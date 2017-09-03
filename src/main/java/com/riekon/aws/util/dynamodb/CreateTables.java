package com.riekon.aws.util.dynamodb;

import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.riekon.aws.model.UserModel;

import java.io.IOException;

public class CreateTables {

    public static void main(String[] args) throws IOException, InterruptedException {
        Connection connection = new Connection();

        CreateTableRequest request = connection.getMapper().generateCreateTableRequest(UserModel.class);
        request.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        connection.getClient().createTable(request).waitForActive();
    }
}
