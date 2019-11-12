package com.whs.warehouse.domain.service;

import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import com.whs.warehouse.domain.data.ContainerStatus;
import com.whs.warehouse.domain.model.Material;
import com.whs.warehouse.infrastructure.repository.MaterialRepository;
import com.whs.warehouse.util.MaterialTestDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MaterialServiceTest {

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
        final Material material = new Material();
        material.setName(materialRequest.getName());

        //When
        when(materialRepository.save(any(Material.class))).thenReturn(materialRequest.requestToMaterial());
        final MaterialResponse materialResponse = materialService.add(materialRequest);

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
        when(materialRepository.save(any(Material.class))).thenReturn(material);
        final MaterialResponse materialResponse = materialService.add(materialRequest);

        //Then
        Assertions.assertNotEquals(materialRequest.getName(), materialResponse.getName());
    }

    @Test
    void getAll() {

        //Given
        final Material material = MaterialTestDataProvider.getMaterial();

        final List<Material> materialList = new ArrayList<>();
        materialList.add(material);

        when(materialRepository.findAll()).thenReturn(materialList);

        //When
        final List<MaterialResponse> materialResponseList = materialService.getAll();

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

        //When
        when(materialRepository.findById(material.getId())).thenReturn(Optional.of(material));

        //Then
        assertEquals(materialResponse, materialService.getById(material.getId()));
        assertEquals(materialResponse.getResponseId(), materialService.getById(material.getId()).getResponseId());
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
        when(materialRepository.findById(materialRequest.requestToMaterial().getId())).thenReturn(Optional.of(materialList.get(0)));

        //Then
        assertEquals(MaterialResponse.materialToResponse(materialList.get(0)), materialService.delete(material.getId()));

    }

    @Test
    void update() {

        final Material material = MaterialTestDataProvider.getMaterial();
        final MaterialRequest materialRequest = new MaterialRequest();
        materialRequest.setId(material.getId());
        materialRequest.setName("TestUpdate");
        materialRequest.setContainer(ContainerStatus.drum);

        //When
        when(materialRepository.findById(material.getId())).thenReturn(Optional.of(material));
        when(materialRepository.save(any())).thenReturn(materialRequest.requestToMaterial());

        final MaterialResponse materialResponse = materialService.update(materialRequest, materialRequest.getId());

        //Then
        assertEquals(materialRequest.getName(), materialResponse.getName());
        assertEquals(materialRequest.getContainer(), materialResponse.getContainer());

    }
}
