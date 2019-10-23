package com.whs.warehouse.infrastructure.material.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Material")
@Getter
@Setter
@NoArgsConstructor
public class Material {
    @Id
    private String id;
    private String name;
    private String description;

    public Material(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
