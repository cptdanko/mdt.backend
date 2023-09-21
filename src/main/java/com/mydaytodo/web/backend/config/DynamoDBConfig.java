package com.mydaytodo.web.backend.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Getter
@Setter
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "dynamo-db")
@EnableDynamoDBRepositories(basePackages = "com.mydaytodo.web.backend.repository")
public class DynamoDBConfig {

    private String amazonDBEndpoint;
    private String key;
    private String secret;
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        /*AmazonDynamoDB dynamoDB= new AmazonDynamoDBClient(amazonAwsCredentials())
                                    .withRegion(Regions.AP_SOUTHEAST_2);*/
        return new AmazonDynamoDBClient()
                .withRegion(Regions.AP_SOUTHEAST_2);


        //return dynamoDB;
    }
    /*@Bean
    public AWSCredentials amazonAwsCredentials() {
        return new BasicAWSCredentials(key, secret);
    }*/
}
