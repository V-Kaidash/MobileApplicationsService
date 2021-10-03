package com.kaidash.mobileapplicationsservice.entity;

public class ApplicationValidationErrorEntity {

    private int status;
    private String name;
    private String version;
    private String contentRate;
    private long timeStamp;

    public ApplicationValidationErrorEntity(){}

    public ApplicationValidationErrorEntity(int status, String name, String version, String contentRate, long timeStamp) {
        this.status = status;
        this.name = name;
        this.version = version;
        this.contentRate = contentRate;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContentRate() {
        return contentRate;
    }

    public void setContentRate(String contentRate) {
        this.contentRate = contentRate;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
