package com.example.shoppinglist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions().disable()) // 👈 Needed for H2 console
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/h2-console/**",       // H2 database UI
                    "/swagger-ui/**",       // Swagger UI
                    "/v3/api-docs/**",      // OpenAPI docs
                    "/api/**"               // Allow your REST API
                ).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults()); // Enables simple login form

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
