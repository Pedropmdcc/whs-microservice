package com.whs.supplier.api.dto;

import com.whs.supplier.infrastructure.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierDtoTest {

    private SupplierDto supplierDtoUnderTest;

    @BeforeEach
    void setUp() {
        supplierDtoUnderTest = new SupplierDto("id", "name", "address", 0);
    }

    @Test
    void testDtoToSupplier() {
        // Setup
        final Supplier expectedResult = new Supplier("id", "name", "address", 0);

        // Run the test
        final Supplier result = supplierDtoUnderTest.dtoToSupplier();

        // Verify the results
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getAddress(), result.getAddress());
        assertEquals(expectedResult.getVatNum(), result.getVatNum());
    }

    @Test
    void testSetId() {
        // Setup
        final String id = "id";

        // Run the test
        supplierDtoUnderTest.setId(id);

        // Verify the results
        assertEquals(supplierDtoUnderTest.getId(), id);
    }

    @Test
    void testSetName() {
        // Setup
        final String name = "name";

        // Run the test
        supplierDtoUnderTest.setName(name);

        // Verify the results
        assertEquals(supplierDtoUnderTest.getName(), name);

    }

    @Test
    void testSetAddress() {
        // Setup
        final String address = "address";

        // Run the test
        supplierDtoUnderTest.setAddress(address);

        // Verify the results
        assertEquals(supplierDtoUnderTest.getAddress(), address);
    }

    @Test
    void testSetVatNum() {
        // Setup
        final int vatNum = 0;

        // Run the test
        supplierDtoUnderTest.setVatNum(vatNum);

        // Verify the results
        assertEquals(supplierDtoUnderTest.getVatNum(), vatNum);

    }
}
