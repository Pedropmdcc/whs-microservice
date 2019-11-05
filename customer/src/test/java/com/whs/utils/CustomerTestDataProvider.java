package com.whs.utils;

import com.whs.customer.api.dto.request.CustomerRequest;
import com.whs.customer.domain.data.TransportZone;
import com.whs.customer.infrastructure.model.Customer;

public class CustomerTestDataProvider {

    public static CustomerRequest getCustomerRequestDto() {
        return CustomerRequest.builder()
                .name("TestName")
                .address("St.Test, n 146 , North")
                .transportZone(TransportZone.NORTH)
                .vat("1234567")
                .build();
    }

    public static Customer getCustomer() {
        return Customer.builder()
                .id("12")
                .name("CustomerTest")
                .address("St.West, 111")
                .transportZone(TransportZone.CENTRAL)
                .vat("321654")
                .build();
    }
}
