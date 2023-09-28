package com.mydaytodo.web.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 *             "id": "wo70-d1kcqHK-SXbbhnIiw",
 *             "alias": "masala-bowl-sydney-sydney-3",
 *             "name": "Masala Bowl Sydney",
 *             "image_url": "https://s3-media4.fl.yelpcdn.com/bphoto/MfyRGaP1aY-E4hmLMUOR5Q/o.jpg",
 *             "is_closed": false,
 *             "url": "https://www.yelp.com/biz/masala-bowl-sydney-sydney-3?adjust_creative=9Pgs_ictXWIkjhrb_aaqgA&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=9Pgs_ictXWIkjhrb_aaqgA",
 *             "review_count": 22,
 *             "categories": [
 *                 {
 *                     "alias": "indpak",
 *                     "title": "Indian"
 *                 }
 *             ],
 *             "rating": 4.0,
 *             "coordinates": {
 *                 "latitude": -33.87771,
 *                 "longitude": 151.20778
 *             },
 *             "transactions": [],
 *             "price": "$$",
 *             "location": {
 *                 "address1": "382A Pitt St",
 *                 "address2": "",
 *                 "address3": "",
 *                 "city": "Sydney",
 *                 "zip_code": "2000",
 *                 "country": "AU",
 *                 "state": "NSW",
 *                 "display_address": [
 *                     "382A Pitt St",
 *                     "Sydney New South Wales 2000",
 *                     "Australia"
 *                 ]
 *             },
 *             "phone": "+61280417988",
 *             "display_phone": "+61 2 8041 7988",
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class YelpSearchResult implements SearchResult {
    private String id;
    private String alias;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("is_closed")
    private boolean isClosed;
    private String url;
    @JsonProperty("review_count")
    private String reviewCount;
    private float rating;
    private String price;
    private String phone;
    @JsonProperty("display_phone")
    private String displayPhone;

}
