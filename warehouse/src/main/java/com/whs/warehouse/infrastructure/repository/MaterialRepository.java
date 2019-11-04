package com.whs.warehouse.infrastructure.repository;

import com.whs.warehouse.infrastructure.model.Material;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends MongoRepository<Material,String> {
    Material findByName(String name);

    Optional<Material> findById(String name);
}
