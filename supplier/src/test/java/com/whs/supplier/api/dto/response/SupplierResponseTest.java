package com.whs.supplier.api.dto.response;

import com.whs.supplier.api.dto.response.SupplierResponse;
import com.whs.supplier.infrastructure.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierResponseTest {

    private SupplierResponse supplierResponseUnderTest;

    @BeforeEach
    void setUp() {
        supplierResponseUnderTest = new SupplierResponse("id", "name", "address", 0);
    }

    @Test
    void testSetId() {
        // Setup
        final String id = "id";

        // Run the test
        supplierResponseUnderTest.setId(id);

        // Verify the results
        assertEquals(supplierResponseUnderTest.getId(), id);
    }

    @Test
    void testSetName() {
        // Setup
        final String name = "name";

        // Run the test
        supplierResponseUnderTest.setName(name);

        // Verify the results
        assertEquals(supplierResponseUnderTest.getName(), name);
    }

    @Test
    void testSetAddress() {
        // Setup
        final String address = "address";

        // Run the test
        supplierResponseUnderTest.setAddress(address);

        // Verify the results
        assertEquals(supplierResponseUnderTest.getAddress(), address);
    }

    @Test
    void testSetVatNum() {
        // Setup
        final int vatNum = 0;

        // Run the test
        supplierResponseUnderTest.setVatNum(vatNum);

        // Verify the results
        assertEquals(supplierResponseUnderTest.getVatNum(), vatNum);
    }

    @Test
    void testSupplierToDto() {
        // Setup
        final Supplier supplier = new Supplier("id", "name", "address", 0);
        final SupplierResponse expectedResult = new SupplierResponse("id", "name", "address", 0);

        // Run the test
        final SupplierResponse result = SupplierResponse.supplierToDto(supplier);

        // Verify the results
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getAddress(), result.getAddress());
        assertEquals(expectedResult.getVatNum(), result.getVatNum());    }

}
