package com.riekon.data.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="BladeMetaData")
public class MetaModel {
    private String uuid;
    private String analytic;
    private String set;
    private String category;
    private MetaInfoEntry metaInfoEntry;
    private MetaLocationEntry metaLocationEntry;
    private MetaAudioEntry metaAudioEntry;

    public MetaModel() { }
    public MetaModel(String uuid) { this.uuid = uuid; }

    @DynamoDBHashKey(attributeName="uuid")
    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    @DynamoDBAttribute(attributeName="analytic")
    public String getAnalytic() { return analytic; }
    public void setAnalytic(String analytic) { this.analytic = analytic; }

    @DynamoDBAttribute(attributeName="set")
    public String getSet() { return set; }
    public void setSet(String set) { this.set = set; }

    @DynamoDBAttribute(attributeName="category")
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public MetaInfoEntry getMetaInfoEntry() { return metaInfoEntry; }
    public void setMetaInfoEntry(MetaInfoEntry metaInfoEntry) { this.metaInfoEntry = metaInfoEntry; }

    public MetaLocationEntry getMetaLocationEntry() { return metaLocationEntry; }
    public void setMetaLocationEntry(MetaLocationEntry metaLocationEntry) { this.metaLocationEntry = metaLocationEntry; }

    public MetaAudioEntry getMetaAudioEntry() { return metaAudioEntry; }
    public void setMetaAudioEntry(MetaAudioEntry metaAudioEntry) { this.metaAudioEntry = metaAudioEntry; }

    @Override
    public String toString() {
        return "MetaModel{" +
                "uuid='" + uuid  +
                ", analytic='" + analytic +
                ", set='" + set +
                ", category='" + category +
                ", metaInfoEntry=" + metaInfoEntry +
                ", metaLocationEntry=" + metaLocationEntry +
                ", metaAudioEntry=" + metaAudioEntry +
                '}';
    }
}
