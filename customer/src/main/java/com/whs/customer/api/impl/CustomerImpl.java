package com.whs.customer.api.impl;

import com.netflix.discovery.EurekaClient;
import com.whs.customer.api.controller.CustomerController;
import com.whs.customer.api.dto.request.CustomerRequest;
import com.whs.customer.api.dto.response.CustomerResponse;
import com.whs.customer.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerImpl implements CustomerController {

    private static final Logger LOGGER = LogManager.getLogger(CustomerImpl.class.getName());

    @Qualifier("eurekaClient")
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    private final CustomerService customerService;

    @Override
    public ResponseEntity<CustomerResponse> create(CustomerRequest customer, UriComponentsBuilder builder) {
        LOGGER.info("Creating new customer: " + customer.getName() + " in the database");
        return customerService.create(customer);
    }

    @Override
    public ResponseEntity<CustomerResponse> findByName(String username){
        LOGGER.info("Searching for username -> " + username);
        return customerService.findByName(username);
    }
}
