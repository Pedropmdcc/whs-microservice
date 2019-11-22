package com.whs.warehouse.domain.data;

/**
 * Class enum with available flag
 */
public enum FlagStatus {
    hazardous("hazardous"),
    flammable("flammable"),
    poisonous("poisonous");

    private final String value;

    FlagStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
