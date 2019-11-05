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

    public List<Material> list() {
        log.info("Listing all courses");
        return materialRepository.findAll();
    }

    public MaterialResponse add(MaterialRequest materialRequest) {
        try {
            return MaterialResponse.materialToResponse(materialRepository.insert(materialRequest.requestToMaterial()));
        } catch (Exception ex) {
            throw new DuplicateRequestException(ex.getMessage());
        }
    }

    public List<MaterialResponse> getAll() {
        List<MaterialResponse> materialResponseList = new ArrayList<>();
        materialRepository.findAll().forEach(e -> materialResponseList.add(MaterialResponse.materialToResponse(e)));
        return materialResponseList;
    }

    public MaterialResponse getById(String id) {
        Material material = materialRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return MaterialResponse.materialToResponse(material);
    }

    public void delete(String id) {
        Material material = materialRepository.findById(id).orElseThrow(() -> new DeleteBadRequestException(id));
        materialRepository.delete(material);
    }

    public MaterialResponse update(MaterialRequest materialRequest, String id) {
        materialRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        Material material = materialRequest.requestToMaterial();
        material.setId(id);
        return MaterialResponse.materialToResponse(materialRepository.save(material));
    }
}
