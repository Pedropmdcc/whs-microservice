package com.whs.supplier.api.dto.request;

import com.whs.supplier.api.dto.request.SupplierRequest;
import com.whs.supplier.infrastructure.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierRequestTest {

    private SupplierRequest supplierRequestUnderTest;

    @BeforeEach
    void setUp() {
        supplierRequestUnderTest = new SupplierRequest("id", "name", "address", 0);
    }

    @Test
    void testDtoToSupplier() {
        // Setup
        final Supplier expectedResult = new Supplier("id", "name", "address", 0);

        // Run the test
        final Supplier result = supplierRequestUnderTest.dtoToSupplier();

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
        supplierRequestUnderTest.setId(id);

        // Verify the results
        assertEquals(supplierRequestUnderTest.getId(), id);
    }

    @Test
    void testSetName() {
        // Setup
        final String name = "name";

        // Run the test
        supplierRequestUnderTest.setName(name);

        // Verify the results
        assertEquals(supplierRequestUnderTest.getName(), name);

    }

    @Test
    void testSetAddress() {
        // Setup
        final String address = "address";

        // Run the test
        supplierRequestUnderTest.setAddress(address);

        // Verify the results
        assertEquals(supplierRequestUnderTest.getAddress(), address);
    }

    @Test
    void testSetVatNum() {
        // Setup
        final int vatNum = 0;

        // Run the test
        supplierRequestUnderTest.setVatNum(vatNum);

        // Verify the results
        assertEquals(supplierRequestUnderTest.getVatNum(), vatNum);

    }
}
