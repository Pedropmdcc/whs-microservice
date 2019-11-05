package com.whs.warehouse.api.impl;

import com.netflix.discovery.EurekaClient;
import com.whs.warehouse.api.controller.MaterialController;
import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import com.whs.warehouse.domain.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
    public ResponseEntity<MaterialResponse> add(@RequestBody MaterialRequest materialRequest, UriComponentsBuilder builder) {
        materialService.add(materialRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/newmaterial/{id}").buildAndExpand(materialRequest.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @Override
    public Resources<MaterialResponse> getAll() {
        List<MaterialResponse> materialList = materialService.getAll();

        for(final MaterialResponse materialResponse : materialList) {
            String materialResponseId = materialResponse.getResponseId();
            Link selfLink = linkTo(methodOn(MaterialController.class).getById(materialResponseId)).withSelfRel();
            materialResponse.add(selfLink);
        }

        Link link = linkTo(MaterialController.class).withSelfRel();
        Resources<MaterialResponse> result = new Resources<>(materialList, link);
        return result;
    }

    @Override
    public ResponseEntity<MaterialResponse> getById(String id) {
        MaterialResponse materialResponse = materialService.getById(id);
        return new ResponseEntity<>(materialResponse, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        materialService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<MaterialResponse> update(MaterialRequest materialRequest, String id) {
        materialService.update(materialRequest, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
