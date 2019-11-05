package com.whs.supplier.api.dto;

import com.whs.supplier.infrastructure.model.Supplier;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDto {

    private String id;
    private String name;
    private String address;
    private int vatNum;

    public Supplier dtoToSupplier() {
        return Supplier.builder()
                .id(this.getId())
                .name(this.getName())
                .address(this.getAddress())
                .vatNum(this.getVatNum())
                .build();
    }
}