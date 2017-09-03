package com.riekon.data.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class MetaAudioEntry {
    String format;
    double sample_rate;
    String encoding;
    String endian;
    String channel;
    Double dc_offset;
    Boolean clipped;

    @DynamoDBAttribute(attributeName="format")
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    @DynamoDBAttribute(attributeName="sample_rate")
    public double getSample_rate() { return sample_rate; }
    public void setSample_rate(double sample_rate) { this.sample_rate = sample_rate; }

    @DynamoDBAttribute(attributeName="encoding")
    public String getEncoding() { return encoding; }
    public void setEncoding(String encoding) { this.encoding = encoding; }

    @DynamoDBAttribute(attributeName="endian")
    public String getEndian() { return endian; }
    public void setEndian(String endian) { this.endian = endian; }

    @DynamoDBAttribute(attributeName="channel")
    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    @DynamoDBAttribute(attributeName="dc_offset")
    public Double getDc_offset() { return dc_offset; }
    public void setDc_offset(Double dc_offset) { this.dc_offset = dc_offset; }

    @DynamoDBAttribute(attributeName="clipped")
    public Boolean getClipped() { return clipped; }
    public void setClipped(Boolean clipped) { this.clipped = clipped; }

    @Override
    public String toString() {
        return "MetaAudioEntry{" +
                "format='" + format +
                ", sample_rate=" + sample_rate +
                ", encoding='" + encoding +
                ", endian='" + endian +
                ", channel='" + channel +
                ", dc_offset=" + dc_offset +
                ", clipped=" + clipped +
                '}';
    }
}
