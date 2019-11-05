package com.whs.customer.api.controller;

import com.whs.customer.api.dto.request.CustomerRequest;
import com.whs.customer.api.dto.response.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
public interface CustomerController {

    /**
     * Adds a new Customer to the database.
     * @param customer Customer object with data.
     * @return  Response with the final result of the operation.
     */
    @PostMapping("/create")
    ResponseEntity<CustomerResponse> save(@RequestBody CustomerRequest customer, UriComponentsBuilder builder);

    /**
     * Search for an existing User.
     * @param username User name.
     * @return Response with result.
     */
    @GetMapping("/search/{username}")
    ResponseEntity<CustomerResponse> findByName(@PathVariable("username") String username);
}
