package com.mydaytodo.web.backend.controller;

import com.mydaytodo.web.backend.restclient.YelpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private YelpClient yelpClient;

    @RequestMapping("/food")
    public ResponseEntity<String> searchRestaurants(@RequestParam(name = "cuisine") String cuisine,
                                                    @RequestParam(name="location", required = false) Optional<String> location) {
        // add validation logic here to validate values
        log.info("The search term is "+ cuisine);
        String searchResult = yelpClient.fetchRestaurants(location, cuisine);
        // temp add, to be updated later
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }
}
