package com.whs.warehouse;

import com.whs.warehouse.api.controller.MaterialController;
import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import com.whs.warehouse.domain.data.ContainerStatus;
import com.whs.warehouse.domain.data.FlagStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class MaterialRequestFunctionalTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(MaterialController.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "test-beans.xml");
    }

    @Inject
    private MaterialController materialController;

    @Test
    public void addMaterialShouldReturnResponseCreated(){
        final List<FlagStatus> flagList = new ArrayList<>();
        flagList.add(FlagStatus.poisonous);
        flagList.add(FlagStatus.hazardous);

        final MaterialRequest materialRequest = MaterialRequest.builder()
                .id("One")
                .name("testFunctional")
                .description("description functional test")
                .weight(4.3)
                .container(ContainerStatus.box)
                .flag(flagList)
                .stackable(true)
                .minimumStock(10)
                .idSupplier("30")
                .build();

        final ResponseEntity<MaterialResponse> materialResponse = materialController.add(materialRequest);

        assertEquals(HttpStatus.CREATED, materialResponse.getStatusCode());
    }

}
