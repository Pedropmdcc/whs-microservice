package com.whs.customer.infrastructure.repository;

import com.whs.customer.infrastructure.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
}
