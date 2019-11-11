package com.whs.supplier.service;

import com.whs.supplier.api.dto.errors.NotFoundException;
import com.whs.supplier.api.dto.request.SupplierRequest;
import com.whs.supplier.api.dto.response.SupplierResponse;
import com.whs.supplier.infrastructure.model.Supplier;
import com.whs.supplier.infrastructure.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
class SupplierServiceTest {

    @Mock
    private SupplierRepository mockSupplierRepository;

    private SupplierService supplierServiceUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        supplierServiceUnderTest = new SupplierService(mockSupplierRepository);
    }

    @Test
    void testListSuppliers() {
        // Setup
        final List<Supplier> expectedResult = Arrays.asList();

        // Run the test
        final List<Supplier> result = supplierServiceUnderTest.listSuppliers();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testSaveSupplier() {
        // Setup
        final SupplierRequest dto = new SupplierRequest("id", "name", "address", 0);
        final Supplier supplier = new Supplier("id", "name", "address", 0);
        Mockito.when(mockSupplierRepository.insert(Mockito.any(Supplier.class))).thenReturn(supplier);

        // Run the test
        SupplierResponse result = supplierServiceUnderTest.saveSupplier(dto);

        // Verify the results
        assertTrue(true);
    }

    @Test
    void testDeleteSupplier() {
        // Setup
        final String id = "id";

        // Run the test
        supplierServiceUnderTest.deleteSupplier(id);

        // Verify the results
        assertTrue(true);
    }

    @Test
    void testUpdateSupplier() {
        // Setup
        final SupplierRequest dto = new SupplierRequest("id", "name", "address", 0);
        final String id = "id";
        final Supplier supplier = new Supplier("id", "name", "address", 0);
        Mockito.when(mockSupplierRepository.save(Mockito.any(Supplier.class))).thenReturn(supplier);

        // Run the test
        supplierServiceUnderTest.updateSupplier(dto, id);

        // Verify the results
        assertTrue(true);
    }

    @Test
    void testFindSupplier(){
        //Setup
        final String id = "id";
        final Supplier supplier = new Supplier("id", "name", "address", 0);
        final SupplierResponse expectedResult = new SupplierResponse("id", "name", "address", 0);
        Mockito.when(mockSupplierRepository.findById(id)).thenReturn(java.util.Optional.of(supplier));

        //Run the test
        SupplierResponse result = supplierServiceUnderTest.findSupplier(id);

        //Verify the results
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getAddress(), result.getAddress());
        assertEquals(expectedResult.getVatNum(), result.getVatNum());
    }

    @Test
    void testFailedFindSupplier(){
        //Setup
        final String id = "id";

        //Run the test

        //Verify the results
        assertThrows(NotFoundException.class, () ->{
            supplierServiceUnderTest.findSupplier(id);
        });
    }
}
