package com.mydaytodo.web.backend.restclient;

import com.mydaytodo.web.backend.config.KeyConfig;
import com.mydaytodo.web.backend.controller.ExternalApiController;
import com.mydaytodo.web.backend.models.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class WeatherClient {
    private final Logger logger = Logger.getLogger(WeatherClient.class.toString());
    @Autowired
    private KeyConfig keyConfig;
    public WeatherResponse getWeather(String city, String units) {
        if(units == null) {
            units = "metric";
        }
        String url = keyConfig.getWeatherUrl() + "?q=" + city +
                "&appid=" + keyConfig.getWeatherKey() +
                "&units=" + units +
                "&lang=" + "AU";
        RestTemplate template = new RestTemplate();
        ResponseEntity<WeatherResponse> response = template.getForEntity(url, WeatherResponse.class);
        return response.getBody();
    }
}
