package com.whs.warehouse.api.impl;

import com.whs.warehouse.api.controller.MaterialController;
import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import com.whs.warehouse.domain.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaterialImpl implements MaterialController {

    private final MaterialService materialService;

    @Override
    public ResponseEntity<MaterialResponse> save(@RequestBody MaterialRequest materialRequest) {
        MaterialResponse materialResponse = materialService.save(materialRequest);
        return new ResponseEntity<>(materialResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<MaterialResponse>>getAll() {
        List<MaterialResponse> materialList = materialService.getAll();
        return new ResponseEntity<>(materialList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MaterialResponse> getById(String id) {
        MaterialResponse materialResponse = materialService.getById(id);
        return new ResponseEntity<>(materialResponse, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<MaterialResponse> delete(String id) {
        MaterialResponse materialResponse = materialService.delete(id);
        return new ResponseEntity<>(materialResponse, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<MaterialResponse> update(MaterialRequest materialRequest, String id) {
        MaterialResponse materialResponse = materialService.update(materialRequest, id);
        return new ResponseEntity<>(materialResponse, HttpStatus.OK);
    }
}
