package com.mydaytodo.web.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    /**
     * Ensure this security config blocks all access to the APIs
     * and only allows access to the those public endpoints for
     * registering and logging in.
     * Rremove deprecated stuff
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //return http
                // other configuration options
            /*authorizeHttpRequests(authCustomizer -> authCustomizer
                    .requestMatchers(HttpMethod.POST, "/api/subscriptions").permitAll()
                    .requestMatchers(
                            "/api/signup", "/api/register", "/api/authenticate"
                    ).permitAll()
                    .requestMatchers("/api/**").authenticated()
            )
            .build();*/
        /*http.authorizeHttpRequests(Customizer.withDefaults())
                        .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                            authorizationManagerRequestMatcherRegistry.requestMatchers("/api/register").permitAll()
                                    .requestMatchers("/api/login").permitAll()
                                    .requestMatchers("/api/todo/**").authenticated();

                        }).build();*/
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
        return http.build();
    }
}