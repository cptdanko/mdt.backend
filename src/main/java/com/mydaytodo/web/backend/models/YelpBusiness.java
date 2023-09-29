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
public class YelpBusiness {
    private String id;
    private String alias;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("is_closed")
    private boolean isClosed;
    private String url;
    @JsonProperty("review_count")
    private String reviewCount;
    private Category[] categories;
    private float rating;
    private String price;
    private String phone;
    @JsonProperty("display_phone")
    private String displayPhone;
    private Location location;
    private String[] transactions;

    @Getter
    @Setter
    @NoArgsConstructor
    static class Location {
        private String address1;
        private String address2;
        private String address3;
        private String city;
        @JsonProperty("zip_code")
        private String zipCode;
        private String country;
        private String state;
        @JsonProperty("display_address")
        private String[] displayAddress;

    }
    @Getter
    @Setter
    @NoArgsConstructor
    static class Coordinates {
        private double latitude;
        private double longitude;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    static class Category {
        private String alias;
        private String title;
    }
}

