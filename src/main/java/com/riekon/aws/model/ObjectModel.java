package com.riekon.aws.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectModel {
    private String uuid;
    private ObjectFileEntry primary;
    private List<ObjectFileEntry> companions = new ArrayList<>();

    public ObjectModel(String uuid) { this.uuid = uuid; }
    public ObjectModel(String uuid, ObjectFileEntry primary) {
        this.uuid = uuid;
        this.primary = primary;
    }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public ObjectFileEntry getPrimary() { return primary; }
    public void setPrimary(ObjectFileEntry primary) { this.primary = primary; }

    public List<ObjectFileEntry> getCompanions() { return companions; }
    public void setCompanions(List<ObjectFileEntry> companions) { this.companions = companions; }

    public void addCompanion(ObjectFileEntry companion) {
        companions.add(companion);
    }
}
