package com.mydaytodo.web.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

@NoArgsConstructor
@Setter
@Getter
public class ExchangeRateResponse {
    private String result;
    private String documentation;
    @JsonProperty("terms_of_use")
    private String termsOfUse;
    @JsonProperty("time_last_update_unix")
    private String timeLastUpdateUnix;
    @JsonProperty("time_last_update_utc")
    private String timeLastUpdateUtc;
    @JsonProperty("time_next_update_unix")
    private String timeNextUpdateUnix;
    @JsonProperty("time_next_update_utc")
    private String timeNextUpdateUtc;
    @JsonProperty("base_code")
    private String baseCode;
    @JsonProperty("conversion_rates")
    Map<String, BigDecimal> conversionRates = new LinkedCaseInsensitiveMap<>();
    //TreeMap<String, BigDecimal> conversionRates = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
}
