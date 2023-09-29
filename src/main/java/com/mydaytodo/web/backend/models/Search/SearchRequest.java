package com.mydaytodo.web.backend.models.Search;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter
@Slf4j
@Builder
@AllArgsConstructor
public class SearchRequest {
    private String userId;
    private String searchTerm;
    private String city;
    private String country;
}
