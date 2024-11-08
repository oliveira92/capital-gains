package com.codechallenge.capitalgains.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Operation {
    private String operation;

    @JsonProperty("unit-cost")
    private double unitCost;

    private Integer quantity;

    public Operation() {
    }

    public Operation(String operation, double unitCost, int quantity) {
        this.operation = operation;
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

}
