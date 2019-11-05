package com.whs.supplier.api.dto;

import com.whs.supplier.infrastructure.model.Supplier;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponseDto {

    private String id;
    private String name;
    private String address;
    private int vatNum;

    public static SupplierResponseDto supplierToDto(Supplier supplier) {
        return SupplierResponseDto.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .address(supplier.getAddress())
                .vatNum(supplier.getVatNum())
                .build();
    }
}
