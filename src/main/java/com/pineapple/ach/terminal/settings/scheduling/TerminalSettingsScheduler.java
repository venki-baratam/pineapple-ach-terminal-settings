package com.pineapple.ach.terminal.settings.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pineapple.ach.terminal.settings.persistence.service.TerminalSettingsInService;

@Component
public class TerminalSettingsScheduler {

    @Autowired
    private TerminalSettingsInService terminalSettingsInService;

    @Scheduled(cron = "0 * * ? * * ")
    public void refreshTerminalData() {

        terminalSettingsInService.refreshTerminalData();

    }

}
