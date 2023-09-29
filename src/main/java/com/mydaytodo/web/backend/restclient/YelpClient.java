package com.mydaytodo.web.backend.restclient;

import com.mydaytodo.web.backend.Constants;
import com.mydaytodo.web.backend.config.KeyConfig;
import com.mydaytodo.web.backend.models.Search.SearchResult;
import com.mydaytodo.web.backend.models.YelpBusinessDetail;
import com.mydaytodo.web.backend.models.Search.YelpSearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
public class YelpClient {
    @Autowired
    private KeyConfig keyConfig;

    WebClient webClient = WebClient.create();

    /**
     * @param location
     * @param term
     * @return
     */
    public Mono<YelpSearchResult> findBusinesses(Optional<String> location, String term) {
        String url = keyConfig.getYelpUrl() +
                    "/businesses/search?"+
                    "location=" +location.orElse("sydney") +
                    "&term=" + term;
        return webClient.get().uri(url)
                .header(Constants.AUTH_HEADER, Constants.BEARER_STR + keyConfig.getYelpToken())
                .retrieve()
                .bodyToMono(YelpSearchResult.class);

    }

    /**
     * @param id
     * @return
     */
    public YelpBusinessDetail getBusinessDetail(String id) {
        String url = keyConfig.getYelpUrl() +
                "/businesses/"+
                id;
        return webClient.get().uri(url)
                .header("Authorization", "Bearer " + keyConfig.getYelpToken())
                .retrieve()
                .bodyToMono(YelpBusinessDetail.class)
                .block();
    }
}
