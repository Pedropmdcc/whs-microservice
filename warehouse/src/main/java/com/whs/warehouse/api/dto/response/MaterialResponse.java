package com.whs.warehouse.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whs.warehouse.domain.data.ContainerStatus;
import com.whs.warehouse.domain.data.FlagStatus;
import com.whs.warehouse.infrastructure.model.Material;
import lombok.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Class that represents the Material request.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialResponse extends ResourceSupport {

    private String id;
    private String name;
    private String description;
    private Double weight;
    private ContainerStatus container;
    private Boolean stackable;
    private List<FlagStatus> flag;
    private Integer minimumStock;
    private String idSupplier;

    public static MaterialResponse materialToResponse(final Material material) {
        return MaterialResponse.builder()
                .id(material.getId())
                .name(material.getName())
                .description(material.getDescription())
                .weight(material.getWeight())
                .container(material.getContainer())
                .stackable(material.getStackable())
                .flag(material.getFlag())
                .minimumStock(material.getMinimumStock())
                .idSupplier(material.getIdSupplier())
                .build();
    }

    @JsonProperty("id")
    public String getResponseId() {
        return this.id;
    }

    @Override
    public Link getId() {
        return Link.valueOf(this.id);
    }
}
