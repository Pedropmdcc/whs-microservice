package com.whs.customer.domain.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Region {


    Aveiro("aveiro"),
    Braga("braga"),
    Braganca("braganca"),
    Coimbra("coimbra"),
    Faro("faro"),
    Leiria("leiria"),
    Porto("porto"),
    Setubal("setubal"),
    VilaReal("vilaReal"),
    CasteloBranco("casteloBranco"),
    Evora("evora"),
    Guarda("guarda"),
    Lisboa("lisboa"),
    Portalegre("portalegre"),
    Santarem("santarem"),
    VianaDoCastelo("vianaDoCastelo"),
    Viseu("viseu"),
    ;

    private String region;

    Region(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public static List<String> asList() {
        return Arrays.asList(values())
                .stream()
                .map(Region::getRegion)
                .collect(Collectors.toCollection(ArrayList::new));
    }


}
