
package com.pineapple.ach.terminal.settings.web.service.validation;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.pineapple.ach.terminal.settings.web.contract.Request;

@Service
public interface ValidationService {

    public Boolean validate(Request request, BindingResult bindingResult);

}
