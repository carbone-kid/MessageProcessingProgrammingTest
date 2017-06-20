package com.sfirsov.processing.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleSale extends Message {
    private double cost;

    @JsonCreator
    public SingleSale(@JsonProperty("productType") String productType, @JsonProperty("cost") double cost) {
        super(productType);
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String toString() {
        return String.format("Single sale: %s; cost: %.2f", getProductType(), getCost());
    }
}
