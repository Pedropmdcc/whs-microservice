package com.whs.warehouse.api.impl;

import com.whs.warehouse.api.controller.MaterialController;
import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import com.whs.warehouse.domain.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaterialControllerImpl implements MaterialController {

    private final MaterialService materialService;

    @Override
    public ResponseEntity<MaterialResponse> save(@RequestBody final MaterialRequest materialRequest) {

        final MaterialResponse materialResponse = this.materialService.save(materialRequest);
        return new ResponseEntity<>(materialResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<MaterialResponse>>getAll() {
        final List<MaterialResponse> materialList = this.materialService.getAll();
        return new ResponseEntity<>(materialList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MaterialResponse> getById(final String id) {
        final MaterialResponse materialResponse = this.materialService.getById(id);
        return new ResponseEntity<>(materialResponse, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> delete(final String id) {
        this.materialService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<MaterialResponse> update(final MaterialRequest materialRequest, final String id) {
        final MaterialResponse materialResponse = this.materialService.update(materialRequest, id);
        return new ResponseEntity<>(materialResponse, HttpStatus.OK);
    }
}
