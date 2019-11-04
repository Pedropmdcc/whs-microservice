package com.whs.warehouse.api.dto.request;

import com.whs.warehouse.domain.data.ContainerStatus;
import com.whs.warehouse.domain.data.FlagStatus;
import com.whs.warehouse.infrastructure.model.Material;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialRequest {
    private String id;
    private String name;
    private String description;
    private Double weight;
    private ContainerStatus container; // enum
    private Boolean stackable;
    private FlagStatus flag; // enum
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
                .flag(this.flag)
                .minimumStock(this.minimumStock)
                .idSupplier(this.idSupplier)
                .build();
    }
}
