package com.mydaytodo.web.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YelpResult implements SearchResult {
    private String id;
    private String alias;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("is_closed")
    private boolean isClosed;
    @JsonProperty("review_count")
    private Long reviewCount;
    private Object categories;
    private Double rating;
    private Object coordinates;
    private Object transactions;
    private double price;
    private Object location;
    private String phone;
    @JsonProperty("display_phone")
    private String displayPhone;
    private Double distance;
}
