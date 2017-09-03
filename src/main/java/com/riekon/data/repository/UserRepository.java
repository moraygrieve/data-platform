package com.riekon.data.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.riekon.data.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private DynamoDBMapper mapper;

    @Autowired
    public UserRepository(AmazonDynamoDB amazonDynamoDB) {
        mapper = new DynamoDBMapper(amazonDynamoDB);
    }

    public UserModel findByName(String name) {
        return mapper.load(UserModel.class, name);
   }
}
