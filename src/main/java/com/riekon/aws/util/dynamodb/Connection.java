package com.riekon.aws.util.dynamodb;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Connection {
    private DynamoDB client;
    private DynamoDBMapper mapper;

    public Connection() throws IOException {
        Properties props = new Properties();
        InputStream resourceStream = CreateTables.class.getResourceAsStream("/application.properties");
        props.load(resourceStream);

        String amazonDynamoDBEndpoint = props.getProperty("amazon.dynamodb.endpoint");
        String amazonAWSAccessKey = props.getProperty("amazon.aws.accesskey");
        String amazonAWSSecretKey = props.getProperty("amazon.aws.secretkey");

        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey));
        amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        client = new DynamoDB(amazonDynamoDB);
        mapper = new DynamoDBMapper(amazonDynamoDB);
    }

    public DynamoDB getClient()  { return client; }
    public DynamoDBMapper getMapper() { return mapper; }
}
