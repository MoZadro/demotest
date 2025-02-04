package com.example.todolist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**", "/", "/add", "/complete/**", "/delete/**").permitAll() // Dozvoli pristup svim endpointima
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**") // Isključi CSRF za H2
            )
            .headers(headers -> headers
                .frameOptions().disable() // Omogući iframe za H2 konzolu
            );
        return http.build();
    }
}