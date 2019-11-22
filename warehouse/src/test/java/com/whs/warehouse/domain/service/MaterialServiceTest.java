package com.whs.warehouse.domain.service;

import com.whs.warehouse.api.controller.MaterialController;
import com.whs.warehouse.api.dto.errors.WeightLimitsException;
import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import com.whs.warehouse.domain.data.ContainerStatus;
import com.whs.warehouse.infrastructure.model.Material;
import com.whs.warehouse.infrastructure.repository.MaterialRepository;
import com.whs.warehouse.util.MaterialTestDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class MaterialServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private MaterialService materialService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addSuccess() {

        //Given
        final MaterialRequest materialRequest = MaterialTestDataProvider.getMaterialRequest();
        final Link link = linkTo(methodOn(MaterialController.class).save(materialRequest)).withSelfRel();

        //When
        when(this.materialRepository.save(any(Material.class))).thenReturn(materialRequest.requestToMaterial());
        final MaterialResponse materialResponse = this.materialService.save(materialRequest);
        materialResponse.add(link);

        //Then
        assertEquals(materialRequest.getName(), materialResponse.getName());
        assertEquals(materialRequest.getId(), materialResponse.getResponseId());
        assertEquals(materialRequest.getContainer(), materialResponse.getContainer());
    }

    @Test
    void addNotSuccess() {

        //Given
        final MaterialRequest materialRequest = MaterialTestDataProvider.getMaterialRequest();
        final Material material = new Material();
        material.setName("WrongName");

        //When
        when(this.materialRepository.save(any(Material.class))).thenReturn(material);
        final MaterialResponse materialResponse = this.materialService.save(materialRequest);

        //Then
        assertNotEquals(materialRequest.getName(), materialResponse.getName());
    }

    @Test
    void addNotSuccessWeightWrong() {

        //Given
        final MaterialRequest materialRequest = MaterialTestDataProvider.getMaterialRequestWrongWeight();

        //When
        when(this.materialRepository.save(any(Material.class))).thenReturn(materialRequest.requestToMaterial());

        //Then
        assertThrows(WeightLimitsException.class, () -> this.materialService.save(materialRequest));
    }

    @Test
    void addNotSuccessDuplicateRequest() {
        final MaterialRequest materialRequestOne = MaterialTestDataProvider.getMaterialRequest();
        final Link linkOne = linkTo(methodOn(MaterialController.class).save(materialRequestOne)).withSelfRel();

        final MaterialRequest materialRequestTwo = MaterialTestDataProvider.getMaterialRequest();
        final Link linkTwo = linkTo(methodOn(MaterialController.class).save(materialRequestTwo)).withSelfRel();

        //When
        when(this.materialRepository.save(any(Material.class))).thenReturn(materialRequestOne.requestToMaterial());
        final MaterialResponse materialResponseOne = this.materialService.save(materialRequestOne);
        materialResponseOne.add(linkOne);
        final MaterialResponse materialResponseTwo = this.materialService.save(materialRequestTwo);
        materialResponseTwo.add(linkTwo);


    }


    @Test
    void getAll() {

        //Given
        final Material material = MaterialTestDataProvider.getMaterial();

        final List<Material> materialList = new ArrayList<>();
        materialList.add(material);

        when(this.materialRepository.findAll()).thenReturn(materialList);

        //When
        final List<MaterialResponse> materialResponseList = this.materialService.getAll();

        //Then
        assertEquals(material.getName(), materialResponseList.get(0).getName());
    }

    @Test
    void getById() {

        //Given
        final MaterialRequest materialRequest = MaterialTestDataProvider.getMaterialRequest();
        final Material material = MaterialTestDataProvider.getMaterial();
        material.setName(materialRequest.getName());
        material.setId(materialRequest.getId());
        final MaterialResponse materialResponse = MaterialResponse.materialToResponse(material);
        final Link link = linkTo(methodOn(MaterialController.class).getById(material.getId())).withSelfRel();
        materialResponse.add(link);

        //When
        when(this.materialRepository.findById(material.getId())).thenReturn(Optional.of(material));

        //Then
        assertEquals(materialResponse, this.materialService.getById(material.getId()));
        assertEquals(materialResponse.getResponseId(), this.materialService.getById(material.getId()).getResponseId());
    }

    @Test
    void delete() {

        //Given
        final MaterialRequest materialRequest = MaterialTestDataProvider.getMaterialRequest();
        final Material material = MaterialTestDataProvider.getMaterial();
        material.setId(materialRequest.getId());
        final List<Material> materialList = new ArrayList<>();
        materialList.add(material);

        //When
        when(this.materialRepository.findById(materialRequest.requestToMaterial().getId())).thenReturn(Optional.of(materialList.get(0)));


        //Then
        assertEquals(MaterialResponse.materialToResponse(materialList.get(0)), this.materialService.delete(materialRequest.getId()));

    }

    @Test
    void update() {

        final Material material = MaterialTestDataProvider.getMaterial();
        final MaterialRequest materialRequest = new MaterialRequest();
        materialRequest.setId(material.getId());
        materialRequest.setName("TestUpdate");
        materialRequest.setContainer(ContainerStatus.drum);

        //When
        when(this.materialRepository.findById(material.getId())).thenReturn(Optional.of(material));
        when(this.materialRepository.save(any())).thenReturn(materialRequest.requestToMaterial());

        final MaterialResponse materialResponse = this.materialService.update(materialRequest, materialRequest.getId());

        //Then
        assertEquals(materialRequest.getName(), materialResponse.getName());
        assertEquals(materialRequest.getContainer(), materialResponse.getContainer());

    }
}
