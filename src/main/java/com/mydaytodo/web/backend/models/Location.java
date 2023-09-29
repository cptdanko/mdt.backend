package com.mydaytodo.web.backend.models;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Getter
@Setter
@Builder
@NoArgsConstructor
@Service
public class Location {

    private static Map<String, String> codeToCountryMap = new HashMap<>();
    private static Map<String, String> countryToCodeMap = new HashMap<>();
    @PostConstruct
    private static void populateCountries() {
        Location.codeToCountryMap = new HashMap<>();
        for(String countryCode: Locale.getISOCountries()) {
            Locale locale = new Locale("", countryCode);
            Location.codeToCountryMap.put(countryCode.toLowerCase(), locale.getDisplayCountry().toLowerCase());
            Location.countryToCodeMap.put(locale.getDisplayCountry().toLowerCase(), countryCode.toLowerCase());
        }
    }
    public static String getCountryFrom(String countryCode) {
        return codeToCountryMap.get(countryCode);
    }
    public static String getCodeFrom(String country) {
        return countryToCodeMap.get(country);

    }


    /**
     * To validate this,
     * 1. check if it's a valid country code
     * 2. Check if it matches at-least 80% of the
     * characters in a given country string
     * @return
     */
    public static boolean isCountry(String searchTerm) {
        if(StringUtils.isEmpty(searchTerm)) return false;
        if(searchTerm.length() < 3) {
            return codeToCountryMap.get(searchTerm.toLowerCase()) != null;
        }
        int eightyPercentChars = Math.abs((searchTerm.length() * 80) / 100);
        return codeToCountryMap.values().stream().anyMatch(c -> {
            if(c.equals(searchTerm.toLowerCase())) {
                return true;
            }
            if(c.length() >= eightyPercentChars) {
                return searchTerm
                        .toLowerCase()
                        .substring(0, eightyPercentChars)
                        .equals(c);
            }
            return false;
        });
    }

    /**
     * @param country
     * @return
     */
    public static String getWeatherUnits(String country) {
        if(country.equalsIgnoreCase("USA")) {
            return "imperial";
        } else {
            return "metric";
        }
    }
    public static boolean isCity(String searchTerm) {
        // add some city validation logic
        return false;
    }
}
