package com.whs.customer.domain.service;

import com.mongodb.MongoException;
import com.whs.customer.api.controller.CustomerController;
import com.whs.customer.api.dto.error.DuplicateResourceException;
import com.whs.customer.api.dto.error.NotFoundException;
import com.whs.customer.api.dto.request.CustomerRequest;
import com.whs.customer.api.dto.response.CustomerResponse;
import com.whs.customer.api.impl.CustomerImpl;
import com.whs.customer.infrastructure.model.Customer;
import com.whs.customer.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
    Customer Service.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {


    private final CustomerRepository customerRepository;
    private static final Logger LOGGER = LogManager.getLogger(CustomerImpl.class.getName());

    /**
     *  Create new Customer in the database.
     * @param customer Customer Request received as input.
     * @return ResponseEntity with status.
     */
    public ResponseEntity<CustomerResponse> save(CustomerRequest customer) {
        LOGGER.info("Creating new customer: " + customer.getName() + " in the database");
        try {
            customerRepository.insert(customer.convertToModel());
            return new ResponseEntity<>(CustomerResponse.convertToResponse(customer.convertToModel()), HttpStatus.CREATED);
        }
        catch (Exception ex) {
            throw new DuplicateResourceException(ex.getLocalizedMessage());
        }
    }

    /**
     * Searches for Customer with given username in database.
     * @param username
     * @return Object Costumer.
     */
    public ResponseEntity<CustomerResponse> findByName(String username){
        LOGGER.info("Searching for username -> " + username);
        Customer customer = customerRepository.findByName(username).orElseThrow(() -> new NotFoundException(username));
        CustomerResponse customerResponse = CustomerResponse.convertToResponse(customer);
        Link link = linkTo(methodOn(CustomerController.class).findByName(username)).withSelfRel();
        customerResponse.add(link);
        return new ResponseEntity<>(customerResponse, HttpStatus.ACCEPTED);
    }

    /**
     * Search for Customer with give id in database.
     * @param id Customer ID.
     * @return Response with HTTP status.
     */
    public ResponseEntity<CustomerResponse> findById(String id) {
        LOGGER.info("Searching for customer id -> " + id);
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        CustomerResponse customerResponse = CustomerResponse.convertToResponse(customer);
        Link link = linkTo(methodOn(CustomerController.class).findById(id)).withSelfRel();
        customerResponse.add(link);
        return new ResponseEntity<>(customerResponse, HttpStatus.ACCEPTED);
    }

    /**
     * Returns all the Customers in database.
     * @return Response with HTTP status.
     */
    public ResponseEntity<List<CustomerResponse>> getAll(){
        LOGGER.info("Getting all Customers");
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        customerRepository.findAll().forEach(e -> customerResponseList.add(CustomerResponse.convertToResponse(e)));
        for(final CustomerResponse customerResponse : customerResponseList) {
            String customerResponseId = customerResponse.getResponseId();
            Link selfLink = linkTo(methodOn(CustomerController.class).findById(customerResponseId)).withSelfRel();
            customerResponse.add(selfLink);
        }
        return new ResponseEntity<>(customerResponseList, HttpStatus.ACCEPTED);
    }

}
