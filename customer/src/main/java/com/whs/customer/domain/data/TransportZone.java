package com.whs.customer.domain.data;

import com.whs.customer.infrastructure.model.Customer;

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
    South("South"),
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
//
//    public static TransportZone setTransportZone(Customer customer) {
//        region = customer.getRegion().toString();
//
//        if (region == "vianaDoCastelo" || region == "braga" || region == "porto" || region == "vilaReal" || region == "braganca")
//            return TransportZone.North;
//
//        if (region == "aveiro" || region =="viseu" || region =="guarda" || region == "coimbra" || region == "casteloBranco" || region == "leiria" || region == "santarem" || region == "lisboa")
//            return TransportZone.Central;
//
//        else
//            return TransportZone.South;
//    }

}
