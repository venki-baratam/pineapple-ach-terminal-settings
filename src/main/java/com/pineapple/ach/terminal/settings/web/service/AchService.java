
package com.pineapple.ach.terminal.settings.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.pineapple.ach.terminal.settings.persistence.entity.TerminalSettingsIn;
import com.pineapple.ach.terminal.settings.persistence.service.TerminalSettingsInService;
import com.pineapple.ach.terminal.settings.web.contract.Request;

@Service
public class AchService {

    @Autowired
    private TerminalSettingsInService terminalSettingsInService;

    public TerminalSettingsIn getCertificationTerminalSettings(Request request,
            BindingResult bindingResult) {
        return terminalSettingsInService.save(request, bindingResult);
    }

}
