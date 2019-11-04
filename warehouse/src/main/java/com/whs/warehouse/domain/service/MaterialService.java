package com.whs.warehouse.domain.service;

import com.whs.warehouse.api.dto.errors.NotFoundException;
import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import com.whs.warehouse.infrastructure.model.Material;
import com.whs.warehouse.infrastructure.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaterialService {

    private final MaterialRepository materialRepository;

    public List<com.whs.warehouse.infrastructure.model.Material> list() {
        log.info("Listing all courses");
        return materialRepository.findAll();
    }

    public void add(MaterialRequest materialRequest) {
        materialRepository.save(materialRequest.dtoToMaterial());
    }

    public List<MaterialResponse> getAll() {

        List<MaterialResponse> materialResponseList = new ArrayList<>();
        materialRepository.findAll().forEach(e -> materialResponseList.add(MaterialResponse.materialToDto(e)));
        return materialResponseList;
    }

    public MaterialResponse getById(String id) {
        Material material = materialRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return MaterialResponse.materialToDto(material);
        //return MaterialResponse.materialToDto(materialRepository.findById(id).get());
    }

    public void delete(String id) {
        Material material = materialRepository.findById(id).get();
        materialRepository.delete(material);
    }

    public void update(MaterialRequest materialRequest, String id) {
        Material material = materialRequest.dtoToMaterial();
        material.setId(id);
        materialRepository.save(material);
    }
}
