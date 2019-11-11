package com.whs.warehouse.domain.service;

import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import com.whs.warehouse.infrastructure.repository.MaterialRepository;
import com.whs.warehouse.util.MaterialTestDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

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
    void add() {

        //Given
        final MaterialRequest materialRequest = MaterialTestDataProvider.getMaterialRequest();
        final MaterialResponse materialResponse = materialService.add(materialRequest);
/*

        //When
        final String materialRequestName = materialRequest.getName();
        final String materialResponseName = materialResponse.getName();
*/

        //Then
        assertEquals(materialRequest.getName(), materialResponse.getName());
        assertEquals(materialRequest.getId(), materialResponse.getResponseId());
        assertEquals(materialRequest.getContainer(), materialResponse.getContainer());
    }

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}
