package com.riekon.data.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class MetaInfoEntry {
    String entry_date;
    String recording_date;
    String device_type;

    @DynamoDBAttribute(attributeName="entry_date")
    public void setEntry_date(String entry_date) { this.entry_date = entry_date; }
    public String getEntry_date() { return entry_date; }

    @DynamoDBAttribute(attributeName="recording_date")
    public void setRecording_date(String recording_date) { this.recording_date = recording_date; }
    public String getRecording_date() { return recording_date; }

    @DynamoDBAttribute(attributeName="device_type")
    public void setDevice_type(String device_type) { this.device_type = device_type; }
    public String getDevice_type() { return device_type; }

    @Override
    public String toString() {
        return "MetaInfoEntry{" +
                "entry_date='" + entry_date +
                ", recording_date='" + recording_date +
                ", device_type='" + device_type +
                '}';
    }
}
