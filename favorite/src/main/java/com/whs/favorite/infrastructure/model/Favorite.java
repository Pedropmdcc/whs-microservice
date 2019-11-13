package com.whs.favorite.infrastructure.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Favorite")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    @Id
    private String id;
    @Indexed(unique=true)
    private String itemId;
    private String name;
    private String iconClass;
    @Indexed(unique=true)
    private String href;
}