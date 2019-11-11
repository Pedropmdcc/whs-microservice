package com.whs.warehouse.domain.service;

import com.whs.warehouse.api.dto.errors.DeleteBadRequestException;
import com.whs.warehouse.api.dto.errors.DuplicateRequestException;
import com.whs.warehouse.api.dto.errors.NotFoundException;
import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import com.whs.warehouse.domain.model.Material;
import com.whs.warehouse.infrastructure.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialResponse add(MaterialRequest materialRequest) {
        log.info("Creating new material.");
        try {
            materialRepository.insert(materialRequest.requestToMaterial());
            return MaterialResponse.materialToResponse(materialRequest.requestToMaterial());
        } catch (Exception ex) {
            throw new DuplicateRequestException(ex.getMessage());
        }
    }

    public List<MaterialResponse> getAll() {
        log.info("Getting all materials.");
        List<MaterialResponse> materialResponseList = new ArrayList<>();
        materialRepository.findAll().forEach(e -> materialResponseList.add(MaterialResponse.materialToResponse(e)));
        return materialResponseList;
    }

    public MaterialResponse getById(String id) {
        log.info("Getting material with the id: " + id);
        Material material = materialRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return MaterialResponse.materialToResponse(material);
    }

    public MaterialResponse delete(String id) {
        log.info("Deleting material with the id: " + id);
        Material material = materialRepository.findById(id).orElseThrow(() -> new DeleteBadRequestException(id));
        materialRepository.delete(material);
        return MaterialResponse.materialToResponse(material);
    }

    public MaterialResponse update(MaterialRequest materialRequest, String id) {
        log.info("Updating material with the id: " + id);
        materialRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        Material material = materialRequest.requestToMaterial();
        material.setId(id);
        return MaterialResponse.materialToResponse(materialRepository.save(material));
    }
}
