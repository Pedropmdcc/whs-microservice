package com.whs.supplier.api.controller;

import com.whs.supplier.api.dto.SupplierDto;
import com.whs.supplier.infrastructure.model.Supplier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface SupplierController {

    @RequestMapping("/suppliers")
    List<Supplier> list();

    @PutMapping("/newSupplier")
    ResponseEntity<Void> insert(@RequestBody SupplierDto dto);

}
