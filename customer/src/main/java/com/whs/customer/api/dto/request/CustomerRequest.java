package com.whs.customer.api.dto.request;

import com.whs.customer.domain.data.Region;
import com.whs.customer.domain.data.TransportZone;
import com.whs.customer.infrastructure.model.Customer;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * Class that represents the Customer request.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest implements Serializable {
    @NotBlank(message = "No name provided")
    private String name;
    @NotNull(message = "No address provided")
    private String address;
    @NotNull(message = "No transport zone provided")
    private TransportZone transportZone;
    @NotNull(message = "No VAT provided")
    private String vat;
    @NotNull(message = "No Region provided")
    private Region region;

    public Customer convertToModel(){
        return Customer.builder()
                .address(this.getAddress())
                .name(this.getName())
                .transportZone(this.getTransportZone())
                .region(this.getRegion())
                .vat(this.getVat())
                .build();
    }
}
