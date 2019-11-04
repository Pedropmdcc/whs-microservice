package com.whs.warehouse.domain.data;

public enum ContainerStatus {
    BAG("bag"),
    DRUM("drum"),
    BOX("box");

    private final String value;

    ContainerStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
