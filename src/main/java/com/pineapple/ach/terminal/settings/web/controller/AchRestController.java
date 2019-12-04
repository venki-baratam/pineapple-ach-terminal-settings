package com.pineapple.ach.terminal.settings.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pineapple.ach.terminal.settings.persistence.entity.TerminalSettingsIn;
import com.pineapple.ach.terminal.settings.web.contract.Request;
import com.pineapple.ach.terminal.settings.web.service.AchService;

@RestController
public class AchRestController {

    @Autowired
    private AchService achService;

    @PostMapping("getCertificationTerminalSettings")
    public ResponseEntity<?> getCertificationTerminalSettings(@Valid @RequestBody Request request, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new TerminalSettingsIn(), HttpStatus.BAD_REQUEST);
        TerminalSettingsIn response = achService.getCertificationTerminalSettings(request, bindingResult);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}