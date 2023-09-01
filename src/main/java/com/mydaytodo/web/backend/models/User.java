package com.mydaytodo.web.backend.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "User")
public class User {
    @DynamoDBAttribute(attributeName = "name")
    private String name;
    @DynamoDBAttribute(attributeName = "username")
    private String username;
    @DynamoDBAttribute(attributeName = "email")
    private String email;
    @DynamoDBHashKey(attributeName = "user_id")
    private String userId;
    /* defaults to the date user record created in system */
    @DynamoDBAttribute(attributeName = "date_joined")
    private Date dateJoined;
    @DynamoDBAttribute(attributeName = "active")
    private Boolean active;
    @DynamoDBAttribute(attributeName = "preferred_currency_code")
    private String preferredCurrencyCode;

    public void transformForUpdate(User user) {
        setActive(user.getActive());
        setDateJoined(user.getDateJoined());
        setPreferredCurrencyCode(user.getPreferredCurrencyCode());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setName(user.getName());
    }
}
