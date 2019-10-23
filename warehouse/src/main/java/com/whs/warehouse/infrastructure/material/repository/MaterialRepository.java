package com.whs.warehouse.infrastructure.material.repository;

import com.whs.warehouse.infrastructure.material.model.Material;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends MongoRepository<Material,String> {
    Material findByName(String name);
}
