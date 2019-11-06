package com.whs.warehouse.api.controller;

import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public interface MaterialController {

    @PostMapping("newmaterial")
    Resource<MaterialResponse> add(@RequestBody MaterialRequest materialRequest, UriComponentsBuilder builder);

    @GetMapping("material")
    Resources<MaterialResponse> getAll();

    @GetMapping("material/{id}")
    Resource<MaterialResponse> getById(@PathVariable("id") String id);

    @DeleteMapping("deletematerial/{id}")
    Resource<MaterialResponse> delete(@PathVariable("id") String id);

    @PutMapping("updatematerial/{id}")
    Resource<MaterialResponse> update(@RequestBody MaterialRequest materialRequest, @PathVariable("id") String id);
}
