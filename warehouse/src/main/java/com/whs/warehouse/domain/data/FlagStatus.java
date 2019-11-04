package com.whs.warehouse.domain.data;

public enum FlagStatus {
    HAZARDOUS("hazardous"),
    FLAMMABLE("flammable"),
    POISONOUS("poisonous");

    private final String value;

    FlagStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
