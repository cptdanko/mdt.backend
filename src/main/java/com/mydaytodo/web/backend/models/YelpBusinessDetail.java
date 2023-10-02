package com.mydaytodo.web.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class YelpBusinessDetail {
    private String id;
    private String alias;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("is_closed")
    private boolean isClosed;
    private String url;
    private String phone;
    @JsonProperty("display_phone")
    private String displayPhone;
    private YelpBusiness.Location location;
    private String[] transactions;
    private float rating;
    private YelpBusiness.Coordinates coordinates;
    public String[] photos;
    private String price;
}
