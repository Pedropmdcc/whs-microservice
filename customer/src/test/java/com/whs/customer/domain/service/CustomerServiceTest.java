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
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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

    @Test
    void getAllCustomers(){

        final Customer customer = CustomerTestDataProvider.getCustomer();
        final List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        when(customerRepositoryMock.findAll()).thenReturn(customerList);

        //when
        final ResponseEntity<List<CustomerResponse>> customerResponseList = customerServiceMock.getAll();

        //then
        assertEquals(customer.getName(), customerResponseList.getBody().get(0).getName());

    }

    @Test
    void getById(){

        final CustomerRequest customerRequest = CustomerTestDataProvider.getCustomerRequestDto();
        final Customer customer = CustomerTestDataProvider.getCustomer();
        customer.setName(customerRequest.getName());
        customer.setVat(customerRequest.getVat());

        final CustomerResponse customerResponse = CustomerResponse.convertToResponse(customer);

        when(customerRepositoryMock.findByName(customer.getName())).thenReturn(Optional.of(customer));

        assertEquals(customerResponse.getName(),customerServiceMock.findByName(customer.getName()).getBody().getName());
        assertEquals(customerResponse.getVat(),customerServiceMock.findByName(customer.getName()).getBody().getVat());
    }

    @Test
    void deleteByName(){

        final Customer customer = CustomerTestDataProvider.getCustomer();
        final ResponseEntity<CustomerResponse> expectedResult = new ResponseEntity<>
                (CustomerResponse.convertToResponse(customer), HttpStatus.ACCEPTED);

        when(customerRepositoryMock.findByName(customer.getName())).thenReturn(Optional.of(customer));

        doNothing().when(customerRepositoryMock).delete(customer);
        final ResponseEntity<CustomerResponse> result = customerServiceMock.delete(customer.getName());

        assertEquals(expectedResult, result);
    }

}
