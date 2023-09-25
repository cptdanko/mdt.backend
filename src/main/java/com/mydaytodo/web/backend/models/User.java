package com.mydaytodo.web.backend.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "User")
@Builder
@AllArgsConstructor
public class User {
    @DynamoDBAttribute(attributeName = "name")
    private String name;
    @NotNull(message = "Username is mandatory")
    @DynamoDBAttribute(attributeName = "username")
    private String username;

    @NotNull(message = "Email is mandatory")
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
    @NotNull(message = "Password is mandatory")
    @DynamoDBAttribute(attributeName = "password")
    private String password;
    // array because a user can have multiple roles
    @DynamoDBAttribute(attributeName = "roles")
    private String[] roles;

    public void transformForUpdate(User user) {
        setActive(user.getActive());
        setDateJoined(user.getDateJoined());
        setPreferredCurrencyCode(user.getPreferredCurrencyCode());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setName(user.getName());
        setRoles(user.getRoles());
    }
}
