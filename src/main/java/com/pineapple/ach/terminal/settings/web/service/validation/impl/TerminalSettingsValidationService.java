
package com.pineapple.ach.terminal.settings.web.service.validation.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.pineapple.ach.terminal.settings.web.contract.Request;
import com.pineapple.ach.terminal.settings.web.service.validation.ValidationService;

@Service
public class TerminalSettingsValidationService implements ValidationService {

    @Override
    public Boolean validate(Request request, BindingResult bindingResult) {
        // Write validations for terminal settings api and populate if any errors in bindingResult
        return true;
    }

}
