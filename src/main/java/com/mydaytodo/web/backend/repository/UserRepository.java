package com.mydaytodo.web.backend.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.mydaytodo.web.backend.config.DynamoDBConfig;
import com.mydaytodo.web.backend.models.User;
import jakarta.annotation.PostConstruct;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class UserRepository {
    private Logger logger = Logger.getLogger(UserRepository.class.getName());
    private AmazonDynamoDB dynamoDB = null;
    @Autowired
    private DynamoDBConfig dynamoDBConfig;
    private DynamoDBMapper mapper = null;

    @PostConstruct
    private void initialiseDB() {
        dynamoDB = dynamoDBConfig.amazonDynamoDB();
        mapper = new DynamoDBMapper(dynamoDB);
    }
    
    public User saveUser(User user) {
        mapper.save(user);
        return user;
    }

    public User getUser(String userId) {
        return mapper.load(User.class, userId);
    }
    public Integer updateUser(String userId, User user) {
        User userInDb = getUser(userId);
        userInDb.transformForUpdate(user);

        DynamoDBSaveExpression saveOp = new DynamoDBSaveExpression()
                .withExpectedEntry("user_id", new ExpectedAttributeValue()
                        .withValue(new AttributeValue(userId)));

        mapper.save(userInDb, saveOp);
        return HttpStatus.SC_NO_CONTENT;
    }

    public Integer deleteUser(String userId) {
        User userInDb = getUser(userId);
        userInDb.setActive(false);
        DynamoDBSaveExpression saveOp = new DynamoDBSaveExpression()
                .withExpectedEntry("user_id", new ExpectedAttributeValue(new AttributeValue().withS(userId)));

        mapper.save(userInDb, saveOp);
        return HttpStatus.SC_NO_CONTENT;


    }
}
