package com.pineapple.ach.terminal.settings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PineappleAchTerminalSettingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PineappleAchTerminalSettingsApplication.class, args);
    }

}
