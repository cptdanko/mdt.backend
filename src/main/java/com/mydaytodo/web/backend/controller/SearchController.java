package com.mydaytodo.web.backend.controller;

import com.mydaytodo.web.backend.models.Search.SearchRequest;
import com.mydaytodo.web.backend.models.Search.SearchResult;
import com.mydaytodo.web.backend.models.YelpBusinessDetail;
import com.mydaytodo.web.backend.restclient.YelpClient;
import com.mydaytodo.web.backend.service.SearchService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private YelpClient yelpClient;

    @Autowired
    private SearchService searchService;
    @GetMapping("/food")
    public ResponseEntity<SearchResult> searchRestaurants(@RequestParam(name = "cuisine") String cuisine,
                                                    @RequestParam(name="location", required = false) Optional<String> location) {
        // add validation logic here to validate values
        log.info("The search term is "+ cuisine);
        SearchResult searchResult = yelpClient
                                    .findBusinesses(location, cuisine)
                                    .block();
        // temp add, to be updated later
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

    @GetMapping("/business/details/{id}")
    public ResponseEntity<YelpBusinessDetail> getBusinessDetail(@PathVariable("id") String id) {
        return new ResponseEntity<>(yelpClient.getBusinessDetail(id), HttpStatus.OK);
    }

    /**
     * Global search across this app, hence
     * do a search on
     * - todo
     * - Weather
     * - news
     * - Chuck Norris Joke (possible?)
     * - Yelp business
     * @param request
     * @return
     */
    @PostMapping("/global")
    public ResponseEntity<SearchResult> doGlobalSearch(@RequestBody SearchRequest request) {
        SearchResult searchResult = searchService.doGlobalSearch(request);
        return new ResponseEntity<>(searchResult, HttpStatus.NOT_FOUND);
    }
}
