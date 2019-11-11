package com.whs.warehouse.util;

import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.domain.data.ContainerStatus;
import com.whs.warehouse.domain.data.FlagStatus;

import java.util.ArrayList;
import java.util.List;

public class MaterialTestDataProvider {

    public static MaterialRequest getMaterialRequest(){
        final List<FlagStatus> flagList = new ArrayList<>();
        flagList.add(FlagStatus.poisonous);
        flagList.add(FlagStatus.hazardous);
        return MaterialRequest.builder()
                .id("100")
                .name("testOne")
                .description("DescriptionTestOne")
                .weight(4.3)
                .container(ContainerStatus.box)
                .stackable(true)
                .flag(flagList)
                .idSupplier("150")
                .build();
    }
}
