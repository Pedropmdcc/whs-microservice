package com.whs.customer.api.dto.request;

import com.whs.customer.domain.data.TransportZone;
import com.whs.customer.infrastructure.model.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Class that represents the Customer request.
 */
@Getter
@Setter
@Builder
public class CustomerRequest {
    @NotBlank(message = "No name provided")
    private String name;
    @NotNull(message = "No address provided")
    private String address;
    @NotNull(message = "No transport zone provided")
    private TransportZone transportZone;
    @NotNull(message = "No VAT provided")
    private String vat;

    public Customer convertToModel(){
        return Customer.builder()
                .address(this.getAddress())
                .name(this.getName())
                .transportZone(this.getTransportZone())
                .vat(this.getVat())
                .build();
    }
}
