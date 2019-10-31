package com.whs.warehouse.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialResponseDto {

    private String id;
    private String name;
    private String description;
    private Double weight;
    private String container; // enum
    private Boolean stackable;
    private String type; // enum
    private Integer minimumStock;
    private String idSupplier; //UUID

    public static MaterialResponseDto materialToDto(com.whs.warehouse.infrastructure.model.Material material) {
        return MaterialResponseDto.builder()
                .id(material.getId())
                .name(material.getName())
                .description(material.getDescription())
                .weight(material.getWeight())
                .container(material.getContainer())
                .stackable(material.getStackable())
                .type(material.getType())
                .minimumStock(material.getMinimumStock())
                .idSupplier(material.getIdSupplier())
                .build();
    }
}
