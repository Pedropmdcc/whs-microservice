package com.whs.warehouse.api.impl;

import com.netflix.discovery.EurekaClient;
import com.whs.warehouse.api.controller.MaterialController;
import com.whs.warehouse.domain.service.MaterialService;
import com.whs.warehouse.infrastructure.model.Material;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaterialImpl implements MaterialController {

    @Qualifier("eurekaClient")
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    private final MaterialService materialService;

    @Override
    public ResponseEntity<Void> add(@RequestBody Material material, UriComponentsBuilder builder) {
        materialService.add(material);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/newmaterial/{id}").buildAndExpand(material.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Material>> getAll() {
        List<Material> materialList = materialService.getAll();
        return new ResponseEntity<>(materialList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Material> getById(String id) {
        Material material = materialService.getById(id);
        return new ResponseEntity<>(material, HttpStatus.OK);
    }
}
