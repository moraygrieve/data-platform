package com.riekon.data.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Set;

@DynamoDBTable(tableName="UserDetails")
public class UserModel {

    private String name;
    private String password;
    private Set<String> roles;

    @DynamoDBHashKey(attributeName="name")
    public String getName() {return name; }
    public void setName(String name) { this.name = name; }

    @DynamoDBAttribute(attributeName="password")
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @DynamoDBAttribute(attributeName="roles")
    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}
