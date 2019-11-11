package com.whs.supplier.api.impl;

import com.netflix.discovery.EurekaClient;
import com.whs.supplier.api.controller.SupplierController;
import com.whs.supplier.api.dto.request.SupplierRequest;
import com.whs.supplier.api.dto.response.SupplierResponse;
import com.whs.supplier.infrastructure.model.Supplier;
import com.whs.supplier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplierImpl implements SupplierController {

    @Qualifier("eurekaClient")
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    private final SupplierService supplierService;

    @Override
    public ResponseEntity<List<Supplier>> listSuppliers() {
        return supplierService.listSuppliers();
    }

    @Override
    public ResponseEntity<SupplierResponse> saveSupplier(SupplierRequest supplierRequest, UriComponentsBuilder builder) {
        SupplierResponse supplierResponse = supplierService.saveSupplier(supplierRequest);
        Link link = linkTo(methodOn(SupplierController.class).saveSupplier(supplierRequest, builder)).withSelfRel();
        supplierResponse.add(link);
        return new ResponseEntity<>(supplierResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SupplierResponse> deleteSupplier(String id){
        SupplierResponse supplierResponse = supplierService.deleteSupplier(id);
        Link link = linkTo(methodOn(SupplierController.class).deleteSupplier(id)).withSelfRel();
        supplierResponse.add(link);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<SupplierResponse> updateSupplier(SupplierRequest supplierRequest, String id){
        SupplierResponse supplierResponse = supplierService.updateSupplier(supplierRequest, id);
        Link link = linkTo(methodOn(SupplierController.class).updateSupplier(supplierRequest, id)).withSelfRel();
        supplierResponse.add(link);
        return new ResponseEntity<>(supplierResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SupplierResponse> findSupplier(String id){
        SupplierResponse supplierResponse = supplierService.findSupplier(id);
        Link link = linkTo(methodOn(SupplierController.class).findSupplier(id)).withSelfRel();
        supplierResponse.add(link);
        return new ResponseEntity<>(supplierResponse, HttpStatus.ACCEPTED);
    }
}
