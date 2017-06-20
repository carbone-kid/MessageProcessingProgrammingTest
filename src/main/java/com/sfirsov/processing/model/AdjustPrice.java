package com.sfirsov.processing.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AdjustPrice extends Message {
    public enum Operation {
        Add,
        Subtract,
        Multiply
    }

    Operation operation;
    double value;

    @JsonCreator
    public AdjustPrice(@JsonProperty("productType") String productType,
                       @JsonProperty("operation") Operation operation,
                       @JsonProperty("value") double value) {
        super(productType);
        this.operation = operation;
        this.value = value;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("adjust price of: %s; %s %.2f",
                getProductType(),
                getOperation(),
                getValue());
    }
}
