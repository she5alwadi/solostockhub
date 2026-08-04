package com.abdul.solostockhub.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.abdul.solostockhub.model.RegistrationForm;
import com.abdul.solostockhub.service.AppUserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    private final AppUserService appUserService;

    public AuthController(
            AppUserService appUserService) {

        this.appUserService = appUserService;
    }

    @GetMapping("/login")
    public String showLoginPage(
            Authentication authentication) {

        if (authentication != null
                && authentication.isAuthenticated()) {

            return "redirect:/";
        }

        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegistrationPage(
            Authentication authentication,
            Model model) {

        if (authentication != null
                && authentication.isAuthenticated()) {

            return "redirect:/";
        }

        model.addAttribute(
                "registrationForm",
                new RegistrationForm()
        );

        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(
            @Valid
            @ModelAttribute("registrationForm")
            RegistrationForm registrationForm,
            BindingResult bindingResult) {

        if (registrationForm.getPassword() != null
                && registrationForm.getConfirmPassword() != null
                && !registrationForm
                        .getPassword()
                        .equals(
                                registrationForm
                                        .getConfirmPassword()
                        )) {

            bindingResult.rejectValue(
                    "confirmPassword",
                    "password.mismatch",
                    "Passwords do not match"
            );
        }

        if (appUserService.usernameExists(
                registrationForm.getUsername())) {

            bindingResult.rejectValue(
                    "username",
                    "username.exists",
                    "That username is already registered"
            );
        }

        if (appUserService.emailExists(
                registrationForm.getEmail())) {

            bindingResult.rejectValue(
                    "email",
                    "email.exists",
                    "That email address is already registered"
            );
        }

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        appUserService.registerCustomer(registrationForm);

        return "redirect:/login?registered=true";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "auth/access-denied";
    }
}