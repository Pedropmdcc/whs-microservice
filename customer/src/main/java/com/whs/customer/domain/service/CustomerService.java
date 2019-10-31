package com.whs.customer.domain.service;

import com.whs.customer.api.dto.request.CustomerRequest;
import com.whs.customer.api.dto.response.CustomerResponse;
import com.whs.customer.infrastructure.model.Customer;
import com.whs.customer.infrastructure.repository.CustomerRepository;
import jdk.management.resource.ResourceRequestDeniedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * @param customer Customer Request received as input.
     * @return
     */
    public ResponseEntity<CustomerResponse> create(CustomerRequest customer) {
        customerRepository.save(customer.convertToModel());
        return new ResponseEntity<>(CustomerResponse.convertToResponse(customer.convertToModel()), HttpStatus.CREATED);
    }

    /**
     * Searches for User in the database.
     * @param username
     * @return Object Custumer.
     */
    public ResponseEntity<CustomerResponse> findByName(String username){
        Customer customer = customerRepository.findByName(username).orElseThrow(() -> new ResourceRequestDeniedException());
        return new ResponseEntity<>(CustomerResponse
                .builder()
                .address(customer.getAddress())
                .name(customer.getName())
                .transportZone(customer.getTransportZone())
                .vat(customer.getVat())
                .build() , HttpStatus.ACCEPTED);
    }

}
