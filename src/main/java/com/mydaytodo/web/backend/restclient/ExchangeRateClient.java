package com.mydaytodo.web.backend.restclient;

import com.mydaytodo.web.backend.config.DefaultConfig;
import com.mydaytodo.web.backend.config.KeyConfig;
import com.mydaytodo.web.backend.models.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.logging.Logger;

@Component
public class ExchangeRateClient {
    private final Logger logger = Logger.getLogger(ExchangeRateClient.class.toString());
    @Autowired
    private KeyConfig config;
    @Autowired
    private DefaultConfig defaultConfig;
    WebClient webClient = WebClient.create();

    /**
     * Get exchangeRates for code defined
     * @param currencyCode
     * @return
     */
    public ExchangeRateResponse exchangeRateData(String currencyCode) {
        if(currencyCode == null) {
            currencyCode = defaultConfig.getCurrencyCode();
        }
        String url = config.getExchangerateUrl() +
                    "/"+ config.getExchangerateKey()
                    + "/latest/" + currencyCode;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .block();

    }
    public BigDecimal convert(BigDecimal amount, String fromCode, String toCode) {
        ExchangeRateResponse response = exchangeRateData(fromCode);
        BigDecimal conversionRate;
        // a hack to tackle case insesitivity
        if(response.getConversionRates().get(toCode) != null) {
            conversionRate = response.getConversionRates().get(toCode);
        } else {
            conversionRate = response.getConversionRates().get(toCode.toUpperCase());
        }
        return amount.multiply(conversionRate);
    }
}
