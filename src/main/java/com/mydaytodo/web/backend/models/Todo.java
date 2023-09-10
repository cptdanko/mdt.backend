package com.mydaytodo.web.backend.models;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "Todo")
public class Todo {
    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBAttribute(attributeName = "date")
    private Date date;

    @DynamoDBAttribute(attributeName = "user_id")
    private String userId;

    @DynamoDBAttribute(attributeName = "done")
    private boolean done;

    @DynamoDBAttribute(attributeName = "text")
    private String text;

    public void transformForUpdate(Todo from) {
        setDate(from.getDate());
        setText(from.getText());
        setDone(from.isDone());
        setUserId(from.getUserId());
    }

}
