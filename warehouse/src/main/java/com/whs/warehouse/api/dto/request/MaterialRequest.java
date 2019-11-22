package com.whs.warehouse.api.dto.request;

import com.whs.warehouse.domain.data.ContainerStatus;
import com.whs.warehouse.domain.data.FlagStatus;
import com.whs.warehouse.infrastructure.model.Material;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the Material request.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialRequest implements Serializable {
    private String id;
    private String name;
    private String description;
    private Double weight;
    private ContainerStatus container;
    private Boolean stackable;
    private Integer minimumStock;
    private String idSupplier;
    private List<FlagStatus> flag = new ArrayList<>();

    public Material requestToMaterial() {
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
