package com.whs.warehouse.domain.data;

/**
 * Class enum with available containers
 */
public enum ContainerStatus {
    bag("bag"),
    drum("drum"),
    box("box");

    private final String value;

    ContainerStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
