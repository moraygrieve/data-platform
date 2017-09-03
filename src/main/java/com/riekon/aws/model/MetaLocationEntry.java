package com.riekon.aws.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import java.util.ArrayList;
import java.util.List;

@DynamoDBDocument
public class MetaLocationEntry {
    String primary;
    List<String> companions = new ArrayList<>();

    @DynamoDBAttribute(attributeName="primary")
    public void setPrimary(String primary) { this.primary = primary; }
    public String getPrimary() { return primary; }

    @DynamoDBAttribute(attributeName="companions")
    public void setCompanions(List<String> companions) { this.companions = companions; }
    public List<String> getCompanions() { return companions;}

    public void addCompanion(String url) { companions.add(url); }

    @Override
    public String toString() {
        return "MetaLocationEntry{" +
                "primary='" + primary +
                '}';
    }
}

