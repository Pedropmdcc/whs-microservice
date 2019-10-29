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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public String getAll() {
        return String.format(
                "Warehousetwo '%s'!", eurekaClient.getApplication(appName).getName());
    }

    @Override
    public List<Material> list() {
        return materialService.list();
    }


}
