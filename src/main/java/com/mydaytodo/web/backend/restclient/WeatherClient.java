package com.mydaytodo.web.backend.restclient;

import com.mydaytodo.web.backend.config.KeyConfig;
import com.mydaytodo.web.backend.controller.ExternalApiController;
import com.mydaytodo.web.backend.models.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class WeatherClient {
    private final Logger logger = Logger.getLogger(WeatherClient.class.toString());
    @Autowired
    private KeyConfig keyConfig;
    WebClient webClient = WebClient.create();

    /**
     * @param city
     * @param units
     * @return
     */
    public Mono<WeatherResponse> getWeather(String city, String units) {
        if(units == null) {
            units = "metric";
        }
        String url = keyConfig.getWeatherUrl() + "?q=" + city +
                "&appid=" + keyConfig.getWeatherKey() +
                "&units=" + units +
                "&lang=" + "AU";
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherResponse.class);
    }
}
