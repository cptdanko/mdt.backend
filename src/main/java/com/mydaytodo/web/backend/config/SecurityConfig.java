package com.mydaytodo.web.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //return http
                // other configuration options
            ./*authorizeHttpRequests(authCustomizer -> authCustomizer
                    .requestMatchers(HttpMethod.POST, "/api/subscriptions").permitAll()
                    .requestMatchers(
                            "/api/signup", "/api/register", "/api/authenticate"
                    ).permitAll()
                    .requestMatchers("/api/**").authenticated()
            )
            .build();*/
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
        return http.build();
    }
}