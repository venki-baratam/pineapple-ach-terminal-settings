package com.pineapple.ach.terminal.settings.soap.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.pineapple.ach.terminal.settings.soap.config.AuthHeader;
import com.pineapple.ach.terminal.settings.soap.model.AuthGatewayHeader;
import com.pineapple.ach.terminal.settings.soap.model.GetCertificationTerminalSettings;
import com.pineapple.ach.terminal.settings.soap.model.GetCertificationTerminalSettingsResponse;

public class SoapClient extends WebServiceGatewaySupport {

    @Value("${soap.request.action.name}")
    private String soapAction;

    public GetCertificationTerminalSettingsResponse getCertificationTerminalSettings(AuthGatewayHeader header) {
        GetCertificationTerminalSettings request = new GetCertificationTerminalSettings();
        GetCertificationTerminalSettingsResponse response = (GetCertificationTerminalSettingsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request, new AuthHeader(header, soapAction + "GetCertificationTerminalSettings"));
        return response;
    }

}