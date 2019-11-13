package com.whs.favorite.infrastructure.repository;

import com.whs.favorite.infrastructure.model.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends MongoRepository<Favorite, String> {
    Optional<Favorite> findById(String name);

    Optional<Favorite> findByItemId(String itemId);
}