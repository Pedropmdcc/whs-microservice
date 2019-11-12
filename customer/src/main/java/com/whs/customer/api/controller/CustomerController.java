package com.whs.customer.api.controller;

import com.whs.customer.api.dto.request.CustomerRequest;
import com.whs.customer.api.dto.response.CustomerResponse;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping(value = "/customer")
public interface CustomerController {

    /**
     * Adds a new Customer to the database.
     * @param customer Customer object with data.
     * @return  Response with HTTP Status.
     */
    @PostMapping
    ResponseEntity<CustomerResponse> save(@RequestBody CustomerRequest customer, UriComponentsBuilder builder);

    /**
     * Search for an existing User.
     * @param username User name.
     * @return Response with HTTP Status.
     */
    @GetMapping("/search/{username}")
    ResponseEntity<CustomerResponse> findByName(@PathVariable("username") String username);

    /**
     * Returns all Customers.
     * @return Response with a List of all Customers.
     */
    @GetMapping
    ResponseEntity<List<CustomerResponse>> getAll();

    /**
     * Search for a Customer with specific ID.
     * @param id of the Customer.
     * @return ResponseEntity with Response Status.
     */
    @GetMapping("/search/user/{id}")
    ResponseEntity<CustomerResponse> findById(@PathVariable("id") String id);
}
