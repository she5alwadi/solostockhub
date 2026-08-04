package com.abdul.solostockhub.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abdul.solostockhub.model.AppUser;
import com.abdul.solostockhub.model.RegistrationForm;
import com.abdul.solostockhub.model.Role;
import com.abdul.solostockhub.repository.AppUserRepository;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder) {

        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean usernameExists(String username) {
        return appUserRepository
                .existsByUsernameIgnoreCase(username);
    }

    public boolean emailExists(String email) {
        return appUserRepository
                .existsByEmailIgnoreCase(email);
    }

    @Transactional
    public AppUser registerCustomer(
            RegistrationForm registrationForm) {

        AppUser user = new AppUser();

        user.setFirstName(
                registrationForm.getFirstName().trim()
        );

        user.setLastName(
                registrationForm.getLastName().trim()
        );

        user.setUsername(
                registrationForm.getUsername().trim()
        );

        user.setEmail(
                registrationForm.getEmail().trim().toLowerCase()
        );

        user.setPassword(
                passwordEncoder.encode(
                        registrationForm.getPassword()
                )
        );

        user.setRole(Role.CUSTOMER);
        user.setEnabled(true);

        return appUserRepository.save(user);
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public long getUserCount() {
        return appUserRepository.count();
    }
}