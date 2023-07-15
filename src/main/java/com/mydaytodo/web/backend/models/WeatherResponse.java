package com.mydaytodo.web.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WeatherResponse {
    private String base;
    private long visibility;
    private long dt;
    private long timezone;
    private String name;
    private long cod;
    private Weather[] weather;
    private Main main;
}
@Setter
@Getter
@NoArgsConstructor
class Weather {
    private long id;
    private String main;
    private String description;
    private String icon;
}
@Setter
@Getter
@NoArgsConstructor
class Main {
    private double temp;
    @JsonProperty("feels_like")
    private double feelsLike;
    @JsonProperty("temp_min")
    private double tempMin;
    @JsonProperty("temp_max")
    private double tempMax;
    private double pressure;
    private double humidity;

}

