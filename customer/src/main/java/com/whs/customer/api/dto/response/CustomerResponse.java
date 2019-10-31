package com.whs.customer.api.dto.response;

import com.whs.customer.domain.data.TransportZone;
import com.whs.customer.infrastructure.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private String name;
    private String address;
    private TransportZone transportZone;
    private String vat;

    public static CustomerResponse convertToResponse(Customer customer){
        return CustomerResponse.builder()
                .address(customer.getAddress())
                .name(customer.getName())
                .transportZone(customer.getTransportZone())
                .vat(customer.getVat())
                .build();
    }

}
