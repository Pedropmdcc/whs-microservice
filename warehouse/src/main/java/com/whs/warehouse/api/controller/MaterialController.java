package com.whs.warehouse.api.controller;

import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/material")
public interface MaterialController {

    @PostMapping
    Resource<MaterialResponse> add(@RequestBody MaterialRequest materialRequest, UriComponentsBuilder builder);

    @GetMapping
    Resources<MaterialResponse> getAll();

    @GetMapping(value = "/{id}")
    Resource<MaterialResponse> getById(@PathVariable("id") String id);

    @DeleteMapping(value = "/{id}")
    Resource<MaterialResponse> delete(@PathVariable("id") String id);

    @PutMapping(value = "/{id}")
    Resource<MaterialResponse> update(@RequestBody MaterialRequest materialRequest, @PathVariable("id") String id);
}
