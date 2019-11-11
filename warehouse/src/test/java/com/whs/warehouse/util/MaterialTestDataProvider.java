package com.whs.warehouse.util;

import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.domain.data.ContainerStatus;

public class MaterialTestDataProvider {

    public static MaterialRequest getMaterialRequest(){

        final MaterialRequest materialRequest = new MaterialRequest();

        materialRequest.setId("100");
        materialRequest.setName("testOne");
        materialRequest.setDescription("DescriptionTestOne");
        materialRequest.setWeight(4.3);
        materialRequest.setContainer(ContainerStatus.box);
        materialRequest.setStackable(true);
        materialRequest.setMinimumStock(10);
        materialRequest.setIdSupplier("150");

        return materialRequest;
    }
}
