package com.mydaytodo.web.backend.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.mydaytodo.web.backend.config.DynamoDBConfig;
import com.mydaytodo.web.backend.models.Todo;
import jakarta.annotation.PostConstruct;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class TodoRepository {
    private final Logger logger = Logger.getLogger(TodoRepository.class.toString());
    private AmazonDynamoDB dynamoDB = null;

    @Autowired
    private DynamoDBConfig dynamoDBConfig;
    private DynamoDBMapper mapper = null;


    @PostConstruct
    private void initialiseDB() {
        dynamoDB = dynamoDBConfig.amazonDynamoDB();
        mapper = new DynamoDBMapper(dynamoDB);
    }
    public Todo saveTodo(Todo todo) {
        mapper.save(todo);
        return todo;
    }
    public Integer deleteTodo(String id) {

        DeleteItemRequest request = new DeleteItemRequest();
        request.setTableName("Todo");
        request.addKeyEntry("id", new AttributeValue().withS(id));
        int opStatus = HttpStatus.SC_NO_CONTENT;
        try {
            dynamoDB.deleteItem(request);
        } catch (Exception e) {
            logger.info("Exception in deleting");
            logger.info(e.getMessage());
            opStatus = HttpStatus.SC_METHOD_FAILURE;
            e.printStackTrace();
        }
        return opStatus;
    }

    /**
     *
     * @param id
     * @return
     */
    public Todo getTodo(String id) {
        return mapper.load(Todo.class, id);
    }
    /**
     *
     * @param userId
     * @return
     */
    public List<Todo> getTodoByUser(String userId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        Todo t = new Todo();
        t.setUserId(userId);
        eav.put(":userId", new AttributeValue().withS(userId));

        DynamoDBQueryExpression<Todo> queryExp = new DynamoDBQueryExpression<Todo>()
                .withIndexName("user_id-index")
                .withKeyConditionExpression("user_id= :userId")
                .withExpressionAttributeValues(eav)
                .withConsistentRead(false);

        return mapper.query(Todo.class, queryExp);
    }

    public Integer updateTodo(String id, Todo updateFrom) {
        Todo todoToUpdate = getTodo(id);
        todoToUpdate.transformForUpdate(updateFrom);

        DynamoDBSaveExpression saveOp = new DynamoDBSaveExpression()
                .withExpectedEntry("id", new ExpectedAttributeValue().withValue(new AttributeValue(id)));
        mapper.save(todoToUpdate, saveOp);
        return HttpStatus.SC_NO_CONTENT;
    }

}
