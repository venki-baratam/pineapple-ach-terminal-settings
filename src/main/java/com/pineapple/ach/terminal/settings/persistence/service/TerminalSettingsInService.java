package com.pineapple.ach.terminal.settings.persistence.service;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.pineapple.ach.terminal.settings.persistence.entity.TerminalSettings;
import com.pineapple.ach.terminal.settings.persistence.entity.TerminalSettingsIn;
import com.pineapple.ach.terminal.settings.persistence.repository.TerminalSettingsInRepository;
import com.pineapple.ach.terminal.settings.soap.client.SoapClient;
import com.pineapple.ach.terminal.settings.soap.contract.TERMINAL_SETTINGS;
import com.pineapple.ach.terminal.settings.soap.model.GetCertificationTerminalSettingsResponse;
import com.pineapple.ach.terminal.settings.utils.FileUtils;
import com.pineapple.ach.terminal.settings.web.contract.Request;
import com.pineapple.ach.terminal.settings.web.service.validation.impl.TerminalSettingsValidationService;

@Service
@Transactional(readOnly = true)
public class TerminalSettingsInService {

    @Autowired
    private TerminalSettingsInRepository terminalSettingsInRepository;

    @Autowired
    private AuthGatewayHeaderService authGatewayHeaderService;

    @Autowired
    private TerminalSettingsValidationService terminalSettingsValidationService;

    @Autowired
    private SoapClient soapClient;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TerminalSettingsService terminalSettingsService;

    public Optional<TerminalSettingsIn> getById(Long id) {

        return terminalSettingsInRepository.findById(id);
    }

    public List<TerminalSettingsIn> getAll() {

        return terminalSettingsInRepository.findAll();
    }

    public List<TerminalSettingsIn> getByActive(Boolean active) {

        return terminalSettingsInRepository.findByActive(active);
    }

    public Long getCountByActive(Boolean active) {

        return terminalSettingsInRepository.countByActive(active);
    }

    @Transactional
    public TerminalSettingsIn save(Request request, BindingResult bindingResult) {

        GetCertificationTerminalSettingsResponse response = null;

        if (terminalSettingsValidationService.validate(request, bindingResult))
            response = soapClient.getCertificationTerminalSettings(authGatewayHeaderService.prepareAuthHeader(request));

        TerminalSettingsIn terminalSettingsIn = prepareTerminalSettings(response);

        populateAuditFields(terminalSettingsIn);

        return terminalSettingsInRepository.save(terminalSettingsIn);

    }

    @Transactional
    public List<TerminalSettingsIn> save(String[] terminalIds) {

        List<TerminalSettingsIn> tsList = new ArrayList<>();
        GetCertificationTerminalSettingsResponse response = null;
        Request request;
        TerminalSettingsIn terminalSettingsIn;
        if (terminalIds != null)
            for (String terminalId : terminalIds) {

                request = new Request();
                request.setTerminalId(Integer.parseInt(terminalId));
                response = soapClient.getCertificationTerminalSettings(authGatewayHeaderService.prepareAuthHeader(request));
                terminalSettingsIn = prepareTerminalSettings(response);
                populateAuditFields(terminalSettingsIn);
                tsList.add(terminalSettingsIn);
            }
        terminalSettingsInRepository.saveAll(tsList);
        terminalSettingsInRepository.flush();
        return tsList;

    }

    @Transactional
    public List<TerminalSettingsIn> save(List<TerminalSettingsIn> terminalSettingsList) {

        return terminalSettingsInRepository.saveAll(terminalSettingsList);

    }

    @Transactional
    public void populateAuditFields(TerminalSettingsIn terminalSettingsIn) {
        terminalSettingsIn.setCreatedOn(new Date());
        terminalSettingsIn.setModifiedOn(new Date());
        EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) entityManager.getEntityManagerFactory();
        String userName = "";
        try {
            userName = info.getPersistenceUnitInfo().getNonJtaDataSource().getConnection().getMetaData().getUserName();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        terminalSettingsIn.setCreatedBy(userName);
        terminalSettingsIn.setModifiedBy(userName);

    }

    @Transactional
    public TerminalSettingsIn prepareTerminalSettings(GetCertificationTerminalSettingsResponse response) {

        JAXBContext jaxbContext;
        TERMINAL_SETTINGS settings = new TERMINAL_SETTINGS();
        try {

            jaxbContext = JAXBContext.newInstance(TERMINAL_SETTINGS.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            settings = (TERMINAL_SETTINGS) jaxbUnmarshaller
                    .unmarshal(new StringReader(response.getGetCertificationTerminalSettingsResult()));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        TerminalSettingsIn terminalSettings = new TerminalSettingsIn(settings);

        terminalSettings.setSchemaFileData(fileUtils.readByURL(terminalSettings.getSchemaFileData()));
        terminalSettings.setXmlTemplateData(fileUtils.readByURL(terminalSettings.getXmlTemplateData()));

        return terminalSettings;
    }

    @Transactional
    public void refreshTerminalData() {

        Resource resource = new ClassPathResource("/static/terminalIds.txt");
        String[] terminalIds = null;

        try {
            File file = resource.getFile();
            String content = new String(Files.readAllBytes(file.toPath()));
            terminalIds = content.split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }

        save(terminalIds);

        if (getCountByActive(true) >= 90) {
            copyActiveRecordsToOriginaTable();

            deActiveAllActiveRecords();
        }
    }

    @Transactional
    public void deActiveAllActiveRecords() {

        List<TerminalSettingsIn> tsList = getByActive(true);
        EntityManagerFactoryInfo info = (EntityManagerFactoryInfo) entityManager.getEntityManagerFactory();
        String userName = "";
        try {
            userName = info.getPersistenceUnitInfo().getNonJtaDataSource().getConnection().getMetaData().getUserName();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (TerminalSettingsIn ts : tsList) {
            ts.setActive(false);
            ts.setDeActivatedBy(userName);
            ts.setDeActivatedOn(new Date());
        }

        terminalSettingsInRepository.saveAll(tsList);
        terminalSettingsInRepository.flush();

    }

    @Transactional
    public void copyActiveRecordsToOriginaTable() {

        List<TerminalSettingsIn> tsList = getByActive(true);

        List<TerminalSettings> originalList = new ArrayList<>();

        for (TerminalSettingsIn ts : tsList)
            originalList.add(new TerminalSettings(ts));

        terminalSettingsService.save(originalList);
        terminalSettingsInRepository.flush();

    }

}
