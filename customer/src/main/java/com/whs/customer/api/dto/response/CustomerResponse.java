package com.whs.customer.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whs.customer.domain.data.TransportZone;
import com.whs.customer.infrastructure.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomerResponse extends ResourceSupport {
    private String id;
    private String name;
    private String address;
    private TransportZone transportZone;
    private String vat;

    public static CustomerResponse convertToResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .address(customer.getAddress())
                .name(customer.getName())
                .transportZone(customer.getTransportZone())
                .vat(customer.getVat())
                .build();
    }

    @JsonProperty("id")
    public String getResponseId() {
        return id;
    }

    @Override
    public Link getId() {
        return Link.valueOf(id);
    }
}
