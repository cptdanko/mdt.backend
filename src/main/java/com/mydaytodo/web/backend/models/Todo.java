package com.mydaytodo.web.backend.models;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Todo {
    private String id;
    private Date date;
    private String userId;
    private boolean done;
    public Todo() {

    }
}
