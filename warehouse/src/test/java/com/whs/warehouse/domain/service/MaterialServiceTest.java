package com.whs.warehouse.domain.service;

import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import com.whs.warehouse.domain.model.Material;
import com.whs.warehouse.infrastructure.repository.MaterialRepository;
import com.whs.warehouse.util.MaterialTestDataProvider;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

public class MaterialServiceTest {


    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private MaterialService materialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void add() {

        //Given
        final MaterialRequest materialRequest = MaterialTestDataProvider.getMaterialRequest();
        final MaterialResponse materialResponse = new MaterialResponse();
        final String materialRequestName = materialRequest.getName();

        //When
        when(materialRepository.insert(any(Material.class))).thenReturn(materialRequest.requestToMaterial());
        //final MaterialResponse materialResponse = materialService.add(materialRequest);


        //Then
        assertEquals(materialRequest.getName(), materialResponse.getId());

        assertEquals(materialRequest.getId(), "materialResponse.getResponseId()");
        assertEquals(materialRequest.getContainer(), materialResponse.getContainer());

    }

    @Test
    public void getAll() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }
}
