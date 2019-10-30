package com.whs.customer.domain.service;

import com.whs.customer.infrastructure.model.Customer;
import com.whs.customer.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
    Customer Service.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {

    private static final Logger LOGGER = LogManager.getLogger(CustomerService.class.getName());
    private final CustomerRepository customerRepository;

    /**
     *  Create new Customer in the database.
     * @param customer
     */
    public void create(Customer customer) {
        customerRepository.save(customer);
    }
}
