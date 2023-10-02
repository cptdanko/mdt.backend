package com.mydaytodo.web.backend.service;

import com.mydaytodo.web.backend.models.ExchangeRateResponse;
import com.mydaytodo.web.backend.models.Location;
import com.mydaytodo.web.backend.models.Search.GlobalSearchResult;
import com.mydaytodo.web.backend.models.Search.SearchRequest;
import com.mydaytodo.web.backend.models.Search.SearchResult;
import com.mydaytodo.web.backend.models.Search.YelpSearchResult;
import com.mydaytodo.web.backend.models.WeatherResponse;
import com.mydaytodo.web.backend.restclient.ExchangeRateClient;
import com.mydaytodo.web.backend.restclient.JokesClient;
import com.mydaytodo.web.backend.restclient.WeatherClient;
import com.mydaytodo.web.backend.restclient.YelpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The service class to collate all the services
 * and collect the data in one place.
 */
@Service
@Slf4j
public class SearchService {
    @Autowired
    private YelpClient yelpClient;
    @Autowired
    private WeatherClient weatherClient;
    private JokesClient jokesClient;
    @Autowired
    private TodoServiceImpl todoService;

    @Autowired
    private ExchangeRateClient exchangeRateClient;

    /**
     * We can assume that the request would contain
     * the name of the city from the client side.
     * The client can get it based on reverse geocoding
     * the coordinates of the device.
     * @param searchRequest
     * @return
     */
    public SearchResult doGlobalSearch(SearchRequest searchRequest) {
        GlobalSearchResult searchResult = new GlobalSearchResult();
        List<Object> responses = new ArrayList<>();
        searchResult.setWebAPIresponses(responses);
        // validate weather or not, it's a counter
        //String searchTerm = searchRequest.getSearchTerm();

        String country = searchRequest.getCountry();
        if(Location.isCountry(country)) {
            String exRateCountryCode = country;
            if(searchRequest.getSearchTerm().length() > 3){
                exRateCountryCode = Location.getCodeFrom(country);
            }
            Mono<ExchangeRateResponse> exRatePromise = exchangeRateClient.exchangeRatePromise(exRateCountryCode);
            exRatePromise.subscribe(responses::add);
        }
        String city = searchRequest.getCity();
        // validate if the search term is a city
        if(Location.isCity(city)) {
            Mono<WeatherResponse> weatherResponseMono = weatherClient.getWeather(city, Location.getWeatherUnits(country));
            weatherResponseMono.subscribe(responses::add);
            // also get yelpResponses
            Mono<YelpSearchResult> yelpPromise = yelpClient.findBusinesses(Optional.of(city), searchRequest.getSearchTerm());
            yelpPromise.subscribe(responses::add);

        }
        searchResult.setTodos(todoService.searchTodo(searchRequest.getUserId(), searchRequest.getSearchTerm()));
        return searchResult;
    }
}
