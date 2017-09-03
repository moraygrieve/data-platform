package com.riekon.data.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QueryRepository {

    @Autowired
    AmazonDynamoDB amazonDynamoDB;
}
