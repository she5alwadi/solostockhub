package com.abdul.solostockhub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(
            CustomUserDetailsService customUserDetailsService) {

        this.customUserDetailsService =
                customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(
                        customUserDetailsService
                );

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            DaoAuthenticationProvider authenticationProvider)
            throws Exception {

        http
                .authenticationProvider(
                        authenticationProvider
                )

                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(
                                "/",
                                "/about",
                                "/contact",
                                "/login",
                                "/register",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/error",
                                "/h2-console/**"
                        ).permitAll()

                        .requestMatchers(
                                "/admin/**",
                                "/products/*/delete"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                "/products/new",
                                "/products/*/edit"
                        ).hasAnyRole(
                                "ADMIN",
                                "STAFF"
                        )

                        .requestMatchers(
                                "/products",
                                "/products/**"
                        ).authenticated()

                        .anyRequest()
                        .authenticated()
                )

                .formLogin(form -> form

                        .loginPage("/login")

                        .loginProcessingUrl("/login")

                        .defaultSuccessUrl(
                                "/",
                                true
                        )

                        .failureUrl(
                                "/login?error=true"
                        )

                        .permitAll()
                )

                .logout(logout -> logout

                        .logoutUrl("/logout")

                        .logoutSuccessUrl(
                                "/login?logout=true"
                        )

                        .invalidateHttpSession(true)

                        .clearAuthentication(true)

                        .deleteCookies("JSESSIONID")

                        .permitAll()
                )

                .exceptionHandling(exception ->
                        exception.accessDeniedPage(
                                "/access-denied"
                        )
                )

                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                "/h2-console/**"
                        )
                )

                .headers(headers -> headers
                        .frameOptions(frame ->
                                frame.sameOrigin()
                        )
                );

        return http.build();
    }
}