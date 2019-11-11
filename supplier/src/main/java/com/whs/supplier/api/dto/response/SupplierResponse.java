package com.whs.supplier.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whs.supplier.infrastructure.model.Supplier;
import lombok.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponse extends ResourceSupport {

    private String id;
    private String name;
    private String address;
    private int vatNum;

    public static SupplierResponse supplierToDto(Supplier supplier) {
        return SupplierResponse.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .address(supplier.getAddress())
                .vatNum(supplier.getVatNum())
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
