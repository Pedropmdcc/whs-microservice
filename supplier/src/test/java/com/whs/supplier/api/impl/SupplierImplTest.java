package com.whs.supplier.api.impl;

import com.whs.supplier.api.dto.request.SupplierRequest;
import com.whs.supplier.infrastructure.model.Supplier;
import com.whs.supplier.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class SupplierImplTest {

    @Mock
    private SupplierService mockSupplierService;

    private SupplierImpl supplierImplUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        supplierImplUnderTest = new SupplierImpl(mockSupplierService);
    }

    @Test
    void testListSuppliers() {
        // Setup
        final List<Supplier> expectedResult = Arrays.asList();

        // Run the test
        final List<Supplier> result = supplierImplUnderTest.listSuppliers();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testSaveSupplier() {
        // Setup
        final SupplierRequest dto = new SupplierRequest("id", "name", "address", 0);
        final ResponseEntity<Void> expectedResult = new ResponseEntity<>(HttpStatus.CREATED);

        // Run the test
        final ResponseEntity<Void> result = supplierImplUnderTest.saveSupplier(dto);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testDeleteSupplier() {
        // Setup
        final String id = "id";
        final ResponseEntity<Void> expectedResult = new ResponseEntity<>(HttpStatus.OK);

        // Run the test
        final ResponseEntity<Void> result = supplierImplUnderTest.deleteSupplier(id);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testUpdateSupplier() {
        // Setup
        final SupplierRequest dto = new SupplierRequest("id", "name", "address", 0);
        final String id = "id";
        final ResponseEntity<Void> expectedResult = new ResponseEntity<>(HttpStatus.OK);

        // Run the test
        final ResponseEntity<Void> result = supplierImplUnderTest.updateSupplier(dto, id);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
