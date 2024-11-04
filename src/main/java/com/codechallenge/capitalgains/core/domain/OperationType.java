package com.codechallenge.capitalgains.core.domain;

public enum OperationType {

    BUY("buy"),
    SELL("sell");

    private final String description;

    OperationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
