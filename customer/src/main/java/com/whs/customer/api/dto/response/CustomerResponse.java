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

    public static CustomerResponse convertToDTO(Customer costumer){
        return new CustomerResponse(costumer.getName(),costumer.getAddress(),costumer.getTransportZone(),costumer.getVat());
    }
}
