package com.whs.customer.domain.service;

import com.whs.customer.api.dto.request.CustomerRequest;
import com.whs.customer.api.dto.response.CustomerResponse;
import com.whs.customer.infrastructure.model.Customer;
import com.whs.customer.infrastructure.repository.CustomerRepository;
import com.whs.utils.CustomerTestDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepositoryMock;

    @InjectMocks
    private CustomerService customerServiceMock;

    @BeforeEach
    void setUp() { MockitoAnnotations.initMocks(this); }

    @Test
    void saveCustomerRequestSuccess() {
        final CustomerRequest customerRequest = CustomerTestDataProvider.getCustomerRequestDto();
        final ResponseEntity<CustomerResponse> customerResponse = customerServiceMock.save(customerRequest);
        //When
        final String customerRequestName = customerRequest.getName();
        final String customerResponseName = customerResponse.getBody().getName();
        //Then
        assertEquals(customerRequestName,customerResponseName);
    }

    @Test
    void saveCustomerRequestFail() {
        final CustomerRequest customerRequest = CustomerTestDataProvider.getCustomerRequestDto();
        when(customerRepositoryMock.save(any(Customer.class))).thenReturn(CustomerTestDataProvider.getCustomer());
        //When
        final Customer customer = customerRepositoryMock.save(customerRequest.convertToModel());
        //Then
        final String customerRequestName = customerRequest.getName();
        final String customerName = customer.getName();
        Assertions.assertNotEquals(customerRequestName,customerName);
    }
}
