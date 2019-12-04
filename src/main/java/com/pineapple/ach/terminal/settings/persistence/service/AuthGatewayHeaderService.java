package com.pineapple.ach.terminal.settings.persistence.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pineapple.ach.terminal.settings.soap.model.AuthGatewayHeader;
import com.pineapple.ach.terminal.settings.soap.model.ObjectFactory;
import com.pineapple.ach.terminal.settings.web.contract.Request;

@Service
@Transactional(readOnly = true)
public class AuthGatewayHeaderService {

    @Value("${soap.request.username}")
    private String userName;

    @Value("${soap.request.password}")
    private String password;

    public AuthGatewayHeader prepareAuthHeader(Request request) {
        ObjectFactory factory = new ObjectFactory();
        AuthGatewayHeader authRequest = factory.createAuthGatewayHeader();
        authRequest.setUserName(userName);
        authRequest.setPassword(password);
        authRequest.setTerminalID(request.getTerminalId());
        return authRequest;
    }

}
