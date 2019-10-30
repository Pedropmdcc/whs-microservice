package com.whs.warehouse.infrastructure.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Material")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Material {
    @Id
    private String id;
    private String name;
    private String description;
    private Double weight;
    private String container; // enum
    private Boolean stackable;
    private String type; // enum
    private Integer minimumStock;
    private String idSupplier; //UUID
}
