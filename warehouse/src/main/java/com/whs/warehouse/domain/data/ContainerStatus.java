package com.whs.warehouse.domain.data;

public enum ContainerStatus {
    bag("bag"),
    drum("drum"),
    box("box");

    private final String value;

    ContainerStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
