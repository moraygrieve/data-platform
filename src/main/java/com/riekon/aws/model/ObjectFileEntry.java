package com.riekon.aws.model;

public class ObjectFileEntry {
    private String uuid;
    private String extension;
    private byte[] bytes;

    public ObjectFileEntry(String uuid, String extension, byte[] bytes) {
        this.uuid = uuid;
        this.extension = extension;
        this.bytes = bytes;
    }

    public String getUuid() { return uuid; }
    public String getExtension() { return extension; }
    public byte[] getBytes() { return bytes; }

    public void setUuid(String uuid) { this.uuid = uuid; }
    public void setExtension(String extension) { this.extension = extension; }
    public void setBytes(byte[] bytes) { this.bytes = bytes; }
}
