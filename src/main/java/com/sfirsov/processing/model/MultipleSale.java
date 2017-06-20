package com.sfirsov.processing.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MultipleSale extends SingleSale {
    private int itemsCount;

    @JsonCreator
    public MultipleSale(@JsonProperty("productType") String productType,
                        @JsonProperty("cost") double cost,
                        @JsonProperty("itemsCount") int itemsCount) {
        super(productType, cost);
        this.itemsCount = itemsCount;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public String toString() {
        return String.format("Multiple sale: %s; count: %d; cost: %.2f", getProductType(), itemsCount, getCost());
    }
}
