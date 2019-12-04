package com.pineapple.ach.terminal.settings.persistence.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pineapple.ach.terminal.settings.persistence.entity.TerminalSettings;
import com.pineapple.ach.terminal.settings.persistence.repository.TerminalSettingsRepository;

@Service
@Transactional(readOnly = true)
public class TerminalSettingsService {

    @Autowired
    private TerminalSettingsRepository terminalSettingsRepository;

    public Optional<TerminalSettings> getById(Long id) {

        return terminalSettingsRepository.findById(id);
    }

    public List<TerminalSettings> getAll() {

        return terminalSettingsRepository.findAll();
    }

    @Transactional
    public List<TerminalSettings> save(List<TerminalSettings> terminalSettingsList) {
        return terminalSettingsRepository.saveAll(terminalSettingsList);

    }

}
