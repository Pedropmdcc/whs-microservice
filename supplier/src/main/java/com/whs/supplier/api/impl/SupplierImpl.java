package com.whs.supplier.api.impl;

import com.netflix.discovery.EurekaClient;
import com.whs.supplier.api.controller.SupplierController;
import com.whs.supplier.api.dto.SupplierDto;
import com.whs.supplier.infrastructure.model.Supplier;
import com.whs.supplier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
    public List<Supplier> listSuppliers() {
        return supplierService.listSuppliers();
    }

    @Override
    public ResponseEntity<Void> saveSupplier(SupplierDto dto) {
        supplierService.saveSupplier(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> cleanSuppliers(){
        supplierService.cleanSuppliers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteSupplier(String id){
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateSupplier(SupplierDto dto, String id){
        supplierService.updateSupplier(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
