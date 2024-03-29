package com.pineapple.ach.terminal.settings.soap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.pineapple.ach.terminal.settings.soap.client.SoapClient;

@Configuration
public class SoapConfig {

    @Value("${soap.request.url}")
    private String soapRequestUrl;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.pineapple.ach.terminal.settings.soap.model");
        return marshaller;
    }

    @Bean
    public SoapClient weatherClient(Jaxb2Marshaller marshaller) {
        SoapClient client = new SoapClient();
        client.setDefaultUri(soapRequestUrl);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}