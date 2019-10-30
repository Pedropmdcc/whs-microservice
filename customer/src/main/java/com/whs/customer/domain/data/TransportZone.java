package com.whs.customer.domain.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TransportZone {
    /**
     * North zone.
     */
    North("North"),
    /**
     * Central zone.
     */
    Central("Central"),
    /**
     * South zone.
     */
    DOLLAR("South"),
    ;

    private String zone;

    TransportZone(String zone) {
        this.zone = zone;
    }

    public String getZone() {
        return zone;
    }

    public static List<String> asList() {
        return Arrays.asList(values())
                .stream()
                .map(TransportZone::getZone)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
