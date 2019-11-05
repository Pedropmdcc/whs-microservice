package com.whs.customer.domain.service;

import com.mongodb.MongoException;
import com.whs.customer.api.dto.error.DuplicateResourceException;
import com.whs.customer.api.dto.error.NotFoundException;
import com.whs.customer.api.dto.request.CustomerRequest;
import com.whs.customer.api.dto.response.CustomerResponse;
import com.whs.customer.infrastructure.model.Customer;
import com.whs.customer.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final CustomerRepository customerRepository;

    /**
     *  Create new Customer in the database.
     * @param customer Customer Request received as input.
     * @return ResponseEntity with status.
     */
    public ResponseEntity<CustomerResponse> save(CustomerRequest customer) {
        try {
            customerRepository.save(customer.convertToModel());
            return new ResponseEntity<>(CustomerResponse.convertToResponse(customer.convertToModel()), HttpStatus.CREATED);
        }
        catch (Exception ex) {
            throw new DuplicateResourceException(ex.getLocalizedMessage());
        }
    }

    /**
     * Searches for given username in the database.
     * @param username
     * @return Object Custumer.
     */
    public ResponseEntity<CustomerResponse> findByName(String username){
        Customer customer = customerRepository.findByName(username).orElseThrow(() -> new NotFoundException(username));
        return new ResponseEntity<>(CustomerResponse.convertToResponse(customer), HttpStatus.ACCEPTED);
    }

}
