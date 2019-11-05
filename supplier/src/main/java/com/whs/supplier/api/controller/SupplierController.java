package com.whs.supplier.api.controller;

import com.whs.supplier.api.dto.request.SupplierRequest;
import com.whs.supplier.api.dto.response.SupplierResponse;
import com.whs.supplier.infrastructure.model.Supplier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface SupplierController {

    @RequestMapping("/suppliers")
    List<Supplier> listSuppliers();

    @PutMapping("/newsupplier")
    ResponseEntity<Void> saveSupplier(@RequestBody SupplierRequest dto);

    @DeleteMapping("/cleansuppliers")
    ResponseEntity<Void> cleanSuppliers();

    @DeleteMapping("/deletesupplier/{id}")
    ResponseEntity<Void> deleteSupplier(@PathVariable String id);

    @PatchMapping("/updatesupplier/{id}")
    ResponseEntity<Void> updateSupplier(@RequestBody SupplierRequest dto, @PathVariable String id);

    @GetMapping("/supplier/{id}")
    ResponseEntity<SupplierResponse> findSupplier(@PathVariable String id);

}
