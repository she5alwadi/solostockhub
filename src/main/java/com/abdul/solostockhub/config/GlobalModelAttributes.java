package com.abdul.solostockhub.config;

import java.util.Arrays;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    private final Environment environment;

    public GlobalModelAttributes(
            Environment environment) {

        this.environment = environment;
    }

    @ModelAttribute("activeProfile")
    public String activeProfile() {

        String[] activeProfiles =
                environment.getActiveProfiles();

        if (activeProfiles.length == 0) {

            activeProfiles =
                    environment.getDefaultProfiles();
        }

        return Arrays.stream(activeProfiles)
                .findFirst()
                .orElse("default")
                .toUpperCase();
    }
}