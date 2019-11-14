package com.whs.customer.domain.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Region {


    Aveiro("Aveiro"),
    Braga("Braga"),
    Braganca("Braganca"),
    Coimbra("Coimbra"),
    Faro("Faro"),
    Leiria("Leiria"),
    Porto("Porto"),
    Setubal("Setubal"),
    VilaReal("VilaReal"),
    CasteloBranco("CasteloBranco"),
    Evora("Evora"),
    Guarda("Guarda"),
    Lisboa("Lisboa"),
    Portalegre("Portalegre"),
    Santarem("Santarem"),
    VianaDoCastelo("VianaDoCastelo"),
    Viseu("Viseu"),
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
