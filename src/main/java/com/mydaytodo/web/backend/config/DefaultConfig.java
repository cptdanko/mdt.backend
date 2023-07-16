package com.mydaytodo.web.backend.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "defaults")
@Getter
@Setter
@NoArgsConstructor
@Configuration
public class DefaultConfig {
    private String currencyCode;
}
