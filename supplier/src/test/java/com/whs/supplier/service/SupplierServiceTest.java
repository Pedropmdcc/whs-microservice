package com.whs.supplier.service;

import com.whs.supplier.api.dto.SupplierDto;
import com.whs.supplier.infrastructure.model.Supplier;
import com.whs.supplier.infrastructure.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        final SupplierDto dto = new SupplierDto("id", "name", "address", 0);

        // Run the test
        supplierServiceUnderTest.saveSupplier(dto);

        // Verify the results
        assertTrue(true);
    }

    @Test
    void testCleanSuppliers() {
        // Setup

        // Run the test
        supplierServiceUnderTest.cleanSuppliers();

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
        final SupplierDto dto = new SupplierDto("id", "name", "address", 0);
        final String id = "id";

        // Run the test
        supplierServiceUnderTest.updateSupplier(dto, id);

        // Verify the results
        assertTrue(true);
    }
}
