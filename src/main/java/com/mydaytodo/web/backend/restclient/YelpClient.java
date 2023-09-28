package com.mydaytodo.web.backend.restclient;

import com.mydaytodo.web.backend.config.KeyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@Slf4j
public class YelpClient {
    @Autowired
    private KeyConfig keyConfig;

    WebClient webClient = WebClient.create();

    /**
     * @param location
     * @param term
     * @return
     */
    public String fetchRestaurants(Optional<String> location, String term) {
        String url = keyConfig.getYelpUrl() +
                    "/businesses/search?"+
                    "location=" +location.orElse("sydney") +
                    "&term=" + term;
        return webClient.get().uri(url)
                .header("Authorization", "Bearer "+ keyConfig.getYelpToken())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
