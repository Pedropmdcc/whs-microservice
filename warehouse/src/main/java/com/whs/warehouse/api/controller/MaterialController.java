package com.whs.warehouse.api.controller;

import com.whs.warehouse.api.dto.MaterialDto;
import com.whs.warehouse.api.dto.MaterialResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public interface MaterialController {

    @PostMapping("/newmaterial")
    ResponseEntity<Void> add(@RequestBody MaterialDto materialDto, UriComponentsBuilder builder);

    @GetMapping("/material")
    ResponseEntity<List<MaterialResponseDto>> getAll();

    @GetMapping("material/{id}")
    ResponseEntity<MaterialResponseDto> getById(@PathVariable("id") String id);
}
