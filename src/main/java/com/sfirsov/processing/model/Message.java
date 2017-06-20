package com.sfirsov.processing.model;

public class Message {
    private String productType;

    public Message(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
