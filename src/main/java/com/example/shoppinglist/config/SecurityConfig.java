package com.example.shoppinglist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())

            .headers(headers -> headers.frameOptions().sameOrigin())

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                // Public APIs remain accessible without login
                .requestMatchers("/api/**").permitAll()

                // Protect Swagger UI and OpenAPI docs
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").authenticated()

                // Protect H2 console
                .requestMatchers("/h2-console/**").authenticated()

                // Everything else requires login
                .anyRequest().authenticated()
            )

            // Form login configuration
            .formLogin(form -> form
                .defaultSuccessUrl("/swagger-ui/index.html", true)
                .permitAll()
            )

            // Logout configuration
            .logout(logout -> logout.permitAll());

        return http.build();
    }
}
