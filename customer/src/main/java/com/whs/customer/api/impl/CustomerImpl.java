package com.whs.customer.api.impl;

import com.whs.customer.api.controller.CustomerController;
import com.whs.customer.api.dto.request.CustomerRequest;
import com.whs.customer.api.dto.response.CustomerResponse;
import com.whs.customer.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerImpl implements CustomerController {

    private final CustomerService customerService;

    @Override
    public ResponseEntity<CustomerResponse> save(CustomerRequest customer, UriComponentsBuilder builder) {
        return customerService.save(customer);
    }

    @Override
    public ResponseEntity<CustomerResponse> findByName(String username){
        return customerService.findByName(username);
    }

    @Override
    public ResponseEntity<CustomerResponse> findById(String id) {
        return customerService.findById(id);
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> getAll(){
        return customerService.getAll();
    }

    @Override
    public ResponseEntity<CustomerResponse> delete(String name){
        return customerService.delete(name);
    }
}
