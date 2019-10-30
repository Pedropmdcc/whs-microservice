package com.whs.customer.domain.service;

import com.whs.customer.api.dto.request.CustomerRequest;
import com.whs.customer.infrastructure.model.Customer;
import com.whs.customer.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
     * @return
     */
    public Customer create(CustomerRequest customer) {
        Customer newCustomer = Customer.builder().
                address(customer.getAddress())
                .name(customer.getName())
                .transportZone(customer.getTransportZone())
                .vat(customer.getVat())
                .build();

        return customerRepository.save(newCustomer);
    }

    /**
     * Searches for User in the database.
     * @param username
     * @return Object Custumer.
     */
    public Optional<Customer> findByName(String username){
        return customerRepository.findByName(username);
    }
}
