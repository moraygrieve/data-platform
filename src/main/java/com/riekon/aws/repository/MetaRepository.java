package com.riekon.aws.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.riekon.aws.model.MetaModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MetaRepository {
    public static final Logger logger = LoggerFactory.getLogger(MetaRepository.class);

    private DynamoDBMapper mapper;

    @Autowired
    public MetaRepository(AmazonDynamoDB amazonDynamoDB) {
        mapper = new DynamoDBMapper(amazonDynamoDB);
    }

    public MetaModel find(String uuid) {
        return mapper.load(MetaModel.class, uuid);
    }

    public void addEntry(MetaModel model) { mapper.save(model); }

    public void updateEntry(MetaModel model) {  mapper.save(model);}

    public void deleteEntry(MetaModel model) {  mapper.delete(model);}
}

