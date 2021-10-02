package com.kaidash.mobileapplicationsservice.entity;

public class ContentRateEntity {

    private String contentRateName;
    private int contentRateAmountFor_3;
    private int contentRateAmountFor_7;
    private int contentRateAmountFor_12;
    private int contentRateAmountFor_16;
    private int contentRateAmountFor_18;

    public ContentRateEntity(){}

    public ContentRateEntity(String contentRateName,
                             int contentRateAmountFor_3,
                             int contentRateAmountFor_7,
                             int contentRateAmountFor_12,
                             int contentRateAmountFor_16,
                             int contentRateAmountFor_18) {
        this.contentRateName = contentRateName;
        this.contentRateAmountFor_3 = contentRateAmountFor_3;
        this.contentRateAmountFor_7 = contentRateAmountFor_7;
        this.contentRateAmountFor_12 = contentRateAmountFor_12;
        this.contentRateAmountFor_16 = contentRateAmountFor_16;
        this.contentRateAmountFor_18 = contentRateAmountFor_18;
    }

    public String getContentRateName() {
        return contentRateName;
    }

    public void setContentRateName(String contentRateName) {
        this.contentRateName = contentRateName;
    }

    public int getContentRateAmountFor_3() {
        return contentRateAmountFor_3;
    }

    public void setContentRateAmountFor_3(int contentRateAmountFor_3) {
        this.contentRateAmountFor_3 = contentRateAmountFor_3;
    }

    public int getContentRateAmountFor_7() {
        return contentRateAmountFor_7;
    }

    public void setContentRateAmountFor_7(int contentRateAmountFor_7) {
        this.contentRateAmountFor_7 = contentRateAmountFor_7;
    }

    public int getContentRateAmountFor_12() {
        return contentRateAmountFor_12;
    }

    public void setContentRateAmountFor_12(int contentRateAmountFor_12) {
        this.contentRateAmountFor_12 = contentRateAmountFor_12;
    }

    public int getContentRateAmountFor_16() {
        return contentRateAmountFor_16;
    }

    public void setContentRateAmountFor_16(int contentRateAmountFor_16) {
        this.contentRateAmountFor_16 = contentRateAmountFor_16;
    }

    public int getContentRateAmountFor_18() {
        return contentRateAmountFor_18;
    }

    public void setContentRateAmountFor_18(int contentRateAmountFor_18) {
        this.contentRateAmountFor_18 = contentRateAmountFor_18;
    }

    @Override
    public String toString() {
        return "ContentRateEntity{" +
                "contentRateName='" + contentRateName + '\'' +
                ", contentRateAmountFor_3=" + contentRateAmountFor_3 +
                ", contentRateAmountFor_7=" + contentRateAmountFor_7 +
                ", contentRateAmountFor_12=" + contentRateAmountFor_12 +
                ", contentRateAmountFor_16=" + contentRateAmountFor_16 +
                ", contentRateAmountFor_18=" + contentRateAmountFor_18 +
                '}';
    }
}
