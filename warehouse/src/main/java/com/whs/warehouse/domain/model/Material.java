package com.whs.warehouse.domain.model;

import com.whs.warehouse.domain.data.ContainerStatus;
import com.whs.warehouse.domain.data.FlagStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
    @Indexed(unique = true)
    private String name;
    private String description;
    private Double weight;
    private ContainerStatus container;
    private Boolean stackable;
    private FlagStatus flag;
    private Integer minimumStock;
    private String idSupplier;

}
