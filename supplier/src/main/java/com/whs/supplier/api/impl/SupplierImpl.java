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

//    @Override
//    public String getAll() {
//        return String.format(
//                "Warehousetwo '%s'!", eurekaClient.getApplication(appName).getName());
//    }

    @Override
    public List<Supplier> list() {
        return supplierService.list();
    }

    @Override
    public ResponseEntity<Void> insert(SupplierDto dto) {
        supplierService.insert(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
