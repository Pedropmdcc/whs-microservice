package com.whs.supplier.api.controller;

import com.whs.supplier.api.dto.SupplierDto;
import com.whs.supplier.infrastructure.model.Supplier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface SupplierController {

    @RequestMapping("/suppliers")
    List<Supplier> listSuppliers();

    @PutMapping("/newSupplier")
    ResponseEntity<Void> saveSupplier(@RequestBody SupplierDto dto);

    @DeleteMapping("/cleanSuppliers")
    ResponseEntity<Void> cleanSuppliers();

    @DeleteMapping("/deleteSupplier/{id}")
    ResponseEntity<Void> deleteSupplier(@PathVariable String id);

    @PatchMapping("/updateSupplier/{id}")
    ResponseEntity<Void> updateSupplier(@RequestBody SupplierDto dto, @PathVariable String id);

}
