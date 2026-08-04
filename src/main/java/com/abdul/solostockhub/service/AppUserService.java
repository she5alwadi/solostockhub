package com.abdul.solostockhub.service;

import java.util.Comparator;
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
        return username != null
                && appUserRepository
                .existsByUsernameIgnoreCase(username.trim());
    }

    public boolean emailExists(String email) {
        return email != null
                && appUserRepository
                .existsByEmailIgnoreCase(email.trim());
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
                registrationForm
                        .getEmail()
                        .trim()
                        .toLowerCase()
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

    @Transactional
    public AppUser createSystemUser(
            String firstName,
            String lastName,
            String username,
            String email,
            String rawPassword,
            Role role) {

        AppUser user = new AppUser();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email.toLowerCase());
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        user.setEnabled(true);

        return appUserRepository.save(user);
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository
                .findAll()
                .stream()
                .sorted(
                        Comparator.comparing(
                                AppUser::getUsername,
                                String.CASE_INSENSITIVE_ORDER
                        )
                )
                .toList();
    }

    public long getUserCount() {
        return appUserRepository.count();
    }
}