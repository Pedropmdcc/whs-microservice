package com.whs.warehouse.api.impl;

import com.netflix.discovery.EurekaClient;
import com.whs.warehouse.api.controller.MaterialController;
import com.whs.warehouse.api.dto.MaterialDto;
import com.whs.warehouse.api.dto.MaterialResponseDto;
import com.whs.warehouse.domain.service.MaterialService;
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
    public ResponseEntity<Void> add(@RequestBody MaterialDto materialDto, UriComponentsBuilder builder) {
        materialService.add(materialDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/newmaterial/{id}").buildAndExpand(materialDto.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<MaterialResponseDto>> getAll() {
        List<MaterialResponseDto> materialList = materialService.getAll();
        return new ResponseEntity<>(materialList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MaterialResponseDto> getById(String id) {
        MaterialResponseDto materialResponseDto = materialService.getById(id);
        return new ResponseEntity<>(materialResponseDto, HttpStatus.OK);
    }
}
