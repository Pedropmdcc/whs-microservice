package com.whs.customer.api;

import com.whs.customer.api.dto.response.CustomerResponse;
import com.whs.customer.domain.service.CustomerService;
import com.whs.customer.infrastructure.model.Customer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(Arquillian.class)
public class CostumerApiFunctionalTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CustomerService.class)
                .addClass(Customer.class)
                .addClass(CustomerResponse.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @Inject
    private Customer customer;

    @Inject
    private CustomerService victim;

    @Test
    public void firstTest() {
        assertEquals("test","test");
    }

    @Test
    public void getAllCostumersShouldReturnResponseOk() {
        final ResponseEntity<List<CustomerResponse>> response = victim.getAll();
        assertEquals(Response.Status.ACCEPTED.getStatusCode(),response.getStatusCode().value());
    }
}
