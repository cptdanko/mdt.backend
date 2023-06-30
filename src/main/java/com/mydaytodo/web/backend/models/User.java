package com.mydaytodo.web.backend.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String name;
    private String username;
    private String email;
    private String userId;
    /* defaults to the date user record created in system */
    private Date dateJoined;
    private Boolean active;
    private String preferredCurrencyCode;
}
