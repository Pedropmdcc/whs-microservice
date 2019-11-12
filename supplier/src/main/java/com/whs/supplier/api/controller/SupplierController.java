package com.whs.supplier.api.controller;

import com.whs.supplier.api.dto.request.SupplierRequest;
import com.whs.supplier.api.dto.response.SupplierResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/suppliers")
public interface SupplierController {

    @GetMapping
    ResponseEntity<List<SupplierResponse>> listSuppliers();

    @PostMapping
    ResponseEntity<SupplierResponse> saveSupplier(@RequestBody SupplierRequest dto, UriComponentsBuilder builder);

    @DeleteMapping("/{id}")
    ResponseEntity<SupplierResponse> deleteSupplier(@PathVariable String id);

    @PutMapping("/{id}")
    ResponseEntity<SupplierResponse> updateSupplier(@RequestBody SupplierRequest dto, @PathVariable String id);

    @GetMapping("/{id}")
    ResponseEntity<SupplierResponse> findSupplier(@PathVariable String id);

}
