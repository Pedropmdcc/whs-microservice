package com.whs.warehouse.api.controller;

import com.whs.warehouse.infrastructure.model.Material;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public interface MaterialController {

    @PostMapping("/newmaterial")
    ResponseEntity<Void> add(@RequestBody Material material, UriComponentsBuilder builder);

    @GetMapping("/material")
    ResponseEntity<List<Material>> getAll();

    @GetMapping("material/{id}")
    ResponseEntity<Material> getById(@PathVariable("id") String id);
}
