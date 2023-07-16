package com.mydaytodo.web.backend.controller;

import com.mydaytodo.web.backend.models.ChuckNorrisJoke;
import com.mydaytodo.web.backend.models.ExchangeRateResponse;
import com.mydaytodo.web.backend.models.WeatherResponse;
import com.mydaytodo.web.backend.restclient.ExchangeRateClient;
import com.mydaytodo.web.backend.restclient.JokesClient;
import com.mydaytodo.web.backend.restclient.WeatherClient;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.logging.Logger;

@RequestMapping("/proxy/api")
@RestController
public class ExternalApiController {
    private final Logger logger = Logger.getLogger(ExternalApiController.class.toString());
    @Autowired
    private JokesClient jokesClient;
    @Autowired
    private WeatherClient weatherClient;
    @Autowired
    public ExchangeRateClient exchangeRateClient;

    /**
     * @return
     */
    @GetMapping("/jokes")
    public ResponseEntity<ChuckNorrisJoke> getJokes() {
        ResponseEntity<String> response;
        return jokesClient.getJokes();
    }

    /**
     * If category is not supplied, it will default to some category
     * @param category
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/jokes/by")
    public ResponseEntity<ChuckNorrisJoke> getJoke(@RequestParam(name="category", required = false) String category) throws InterruptedException {
        // if the category ids not present pick a random from the list
        return this.jokesClient.getJokes(category);
    }

    /**
     * default the weather to Sydney
     * @return
     */
    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeather(@RequestParam(name = "city", required = false) String city, @RequestParam(name = "units", required = false) String units) {
        // add some validation here to validate cities
        ResponseEntity<String> response;
        if(city == null) {
            city = "sydney";
        }
        return new ResponseEntity<>(weatherClient.getWeather(city, units), HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/exchangeRate")
    public ResponseEntity<ExchangeRateResponse> getExchangeRates(@RequestParam(value = "currencyCode", required = false) String currencyCode) {
        // validate the currency code
        ExchangeRateResponse response = exchangeRateClient.exchangeRateData(currencyCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/exchangeRate/convert/{amount}/from/{fromCode}/to/{toCode}")
    public ResponseEntity<BigDecimal> convert(@PathVariable("amount") BigDecimal amount, @PathVariable("fromCode") String fromCode, @PathVariable("toCode") String toCode ) {
        // add validation logic
        BigDecimal convertedAmt = exchangeRateClient.convert(amount, fromCode, toCode);
        return new ResponseEntity<>(convertedAmt, HttpStatus.OK);
    }
}
