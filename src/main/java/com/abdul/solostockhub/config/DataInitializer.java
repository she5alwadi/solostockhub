package com.abdul.solostockhub.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.abdul.solostockhub.model.Role;
import com.abdul.solostockhub.service.AppUserService;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner createInitialUsers(
            AppUserService appUserService) {

        return args -> {

            if (!appUserService.usernameExists("admin")) {

                appUserService.createSystemUser(
                        "SoloStock",
                        "Administrator",
                        "admin",
                        "admin@solostockhub.com",
                        "Admin123!",
                        Role.ADMIN
                );
            }

            if (!appUserService.usernameExists("staff")) {

                appUserService.createSystemUser(
                        "Warehouse",
                        "Staff",
                        "staff",
                        "staff@solostockhub.com",
                        "Staff123!",
                        Role.STAFF
                );
            }

            if (!appUserService.usernameExists("customer")) {

                appUserService.createSystemUser(
                        "Sample",
                        "Customer",
                        "customer",
                        "customer@solostockhub.com",
                        "Customer123!",
                        Role.CUSTOMER
                );
            }
        };
    }
}