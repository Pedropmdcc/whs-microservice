package com.whs.supplier.api.dto.response;

import com.whs.supplier.infrastructure.model.Supplier;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponse {

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
}
