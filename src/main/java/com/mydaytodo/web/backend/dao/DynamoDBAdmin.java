package com.mydaytodo.web.backend.dao;

import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.mydaytodo.web.backend.config.DynamoDBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static java.lang.String.*;

@Component
public class DynamoDBAdmin {
    private Logger logger = Logger.getLogger(DynamoDBAdmin.class.toString());
    @Autowired
    private DynamoDBConfig ddbConfig;

    public void listTables() {
        ListTablesRequest request = new ListTablesRequest().clone();
        logger.info("the amount of tables is");
        logger.info(format("%d", ddbConfig.amazonDynamoDB().listTables().getTableNames().size()));
        ddbConfig.amazonDynamoDB().listTables().getTableNames().forEach(tableName -> {
            logger.info("The table name is");
            logger.info(tableName);
        });

        logger.info("Now querying through the table request");
        ddbConfig.amazonDynamoDB().listTables(request).getTableNames().forEach(tableName -> {
            logger.info("The table name is");
            logger.info(tableName);
        });
    }
}
