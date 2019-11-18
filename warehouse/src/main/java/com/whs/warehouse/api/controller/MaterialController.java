package com.whs.warehouse.api.controller;

import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/material")
public interface MaterialController {

    @PostMapping
    ResponseEntity<MaterialResponse> save(@RequestBody MaterialRequest materialRequest);

    @GetMapping
    ResponseEntity<List<MaterialResponse>> getAll();

    @GetMapping(value = "/{id}")
    ResponseEntity<MaterialResponse> getById(@PathVariable("id") String id);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<MaterialResponse> delete(@PathVariable("id") String id);

    @PutMapping(value = "/{id}")
    ResponseEntity<MaterialResponse> update(@RequestBody MaterialRequest materialRequest, @PathVariable("id") String id);
}
