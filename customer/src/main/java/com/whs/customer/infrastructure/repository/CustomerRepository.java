package com.whs.customer.infrastructure.repository;

import com.whs.customer.infrastructure.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
    Optional<Customer> findByName(String name);
}
