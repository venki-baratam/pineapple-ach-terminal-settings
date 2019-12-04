package com.pineapple.ach.terminal.settings.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pineapple.ach.terminal.settings.soap.contract.TERMINAL_SETTINGS;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Table(name = "terminal_settings_in")
@SequenceGenerator(name = TerminalSettingsIn.SEQ_TERMINAL_SETTINGS_IN, sequenceName = TerminalSettingsIn.SEQ_TERMINAL_SETTINGS_IN, allocationSize = 1)
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Entity
public class TerminalSettingsIn extends AbstractAuditable {

    private static final long serialVersionUID = -3999081085363655728L;

    public static final String SEQ_TERMINAL_SETTINGS_IN = "SEQ_TERMINAL_SETTINGS_IN";

    @Id
    @GeneratedValue(generator = SEQ_TERMINAL_SETTINGS_IN, strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(name = "terminal_id")
    private Long terminalId;

    @NotNull
    @Column(name = "sec_code")
    private String secCode;

    @NotNull
    @Column(name = "is_gateway_terminal")
    private Boolean isGatewayTerminal;

    @NotNull
    @Column(name = "allow_consumer_credits")
    private Boolean allowConsumerCredits;

    @NotNull
    @Column(name = "dl_required")
    private Boolean dlRequired;

    @NotNull
    @Column(name = "run_check_verification")
    private Boolean runCheckVerification;

    @NotNull
    @Column(name = "run_identity_verification")
    private Boolean runIdentityVerification;

    @NotNull
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "schema_xsd_data")
    private String schemaFileData;

    @NotNull
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "xml_template_data")
    private String xmlTemplateData;

    private Boolean active = true;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deactivated_on")
    private Date deActivatedOn;

    @Column(name = "deactivated_by")
    private String deActivatedBy;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public Boolean getIsGatewayTerminal() {
        return isGatewayTerminal;
    }

    public void setIsGatewayTerminal(Boolean isGatewayTerminal) {
        this.isGatewayTerminal = isGatewayTerminal;
    }

    public Boolean getAllowConsumerCredits() {
        return allowConsumerCredits;
    }

    public void setAllowConsumerCredits(Boolean allowConsumerCredits) {
        this.allowConsumerCredits = allowConsumerCredits;
    }

    public Boolean getDlRequired() {
        return dlRequired;
    }

    public void setDlRequired(Boolean dlRequired) {
        this.dlRequired = dlRequired;
    }

    public Boolean getRunCheckVerification() {
        return runCheckVerification;
    }

    public void setRunCheckVerification(Boolean runCheckVerification) {
        this.runCheckVerification = runCheckVerification;
    }

    public Boolean getRunIdentityVerification() {
        return runIdentityVerification;
    }

    public void setRunIdentityVerification(Boolean runIdentityVerification) {
        this.runIdentityVerification = runIdentityVerification;
    }

    public String getSchemaFileData() {
        return schemaFileData;
    }

    public void setSchemaFileData(String schemaFileData) {
        this.schemaFileData = schemaFileData;
    }

    public String getXmlTemplateData() {
        return xmlTemplateData;
    }

    public void setXmlTemplateData(String xmlTemplateData) {
        this.xmlTemplateData = xmlTemplateData;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDeActivatedOn() {
        return deActivatedOn;
    }

    public void setDeActivatedOn(Date deActivatedOn) {
        this.deActivatedOn = deActivatedOn;
    }

    public String getDeActivatedBy() {
        return deActivatedBy;
    }

    public void setDeActivatedBy(String deActivatedBy) {
        this.deActivatedBy = deActivatedBy;
    }

    public TerminalSettingsIn() {
    }

    public TerminalSettingsIn(TERMINAL_SETTINGS contract) {
        terminalId = contract.getTERMINAL_ID();
        secCode = contract.getSEC_CODE();
        isGatewayTerminal = contract.getIS_GATEWAY_TERMINAL();
        allowConsumerCredits = contract.getALLOW_CNSMR_CREDITS();
        dlRequired = contract.getDL_REQUIRED();
        runCheckVerification = contract.getRUN_CHECK_VERIFICATION();
        runIdentityVerification = contract.getRUN_IDENTITY_VERIFICATION();
        schemaFileData = contract.getSCHEMA_FILE_PATH();
        xmlTemplateData = contract.getXML_TEMPLATE_PATH();
    }

}