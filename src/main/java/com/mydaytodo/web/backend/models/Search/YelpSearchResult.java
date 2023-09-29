package com.mydaytodo.web.backend.models.Search;

import com.mydaytodo.web.backend.models.YelpBusiness;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class YelpSearchResult implements SearchResult{
    private YelpBusiness[] businesses;
}
