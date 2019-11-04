package com.whs.warehouse.api.controller;

import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public interface MaterialController {

    @PostMapping("/newmaterial")
    ResponseEntity<Void> add(@RequestBody MaterialRequest materialRequest, UriComponentsBuilder builder);

    @GetMapping("/material")
    ResponseEntity<List<MaterialResponse>> getAll();

    @GetMapping("material/{id}")
    ResponseEntity<Object> getById(@PathVariable("id") String id);

    @DeleteMapping("deletematerial/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") String id);

    @PutMapping("updatematerial/{id}")
    ResponseEntity<Void> update(@RequestBody MaterialRequest materialRequest, @PathVariable("id") String id);
}
