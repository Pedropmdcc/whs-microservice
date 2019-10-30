package com.whs.customer.api.controller;

import com.whs.customer.infrastructure.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
public interface CustomerController {

    /**
     * Adds a new Customer to the database.
     * @param customer Customer object with data.
     * @return  Response with the final result of the operation.
     */
    @PostMapping("/create")
    ResponseEntity<Void> create(@RequestBody Customer customer, UriComponentsBuilder builder);

}
