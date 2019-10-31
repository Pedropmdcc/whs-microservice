package com.whs.warehouse.api.dto;

import com.whs.warehouse.infrastructure.model.Material;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialDto {
    private String id;
    private String name;
    private String description;
    private Double weight;
    private String container; // enum
    private Boolean stackable;
    private String type; // enum
    private Integer minimumStock;
    private String idSupplier; //UUID

    public Material dtoToMaterial() {
        return Material.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .weight(this.weight)
                .container(this.container)
                .stackable(this.stackable)
                .type(this.type)
                .minimumStock(this.minimumStock)
                .idSupplier(this.idSupplier)
                .build();
    }
}
