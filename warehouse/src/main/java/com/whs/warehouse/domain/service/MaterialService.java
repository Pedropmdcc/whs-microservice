package com.whs.warehouse.domain.service;

import com.mongodb.MongoWriteConcernException;
import com.whs.warehouse.api.controller.MaterialController;
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
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final Double MINIMUM = 10.0;
    private final Double MAXIMUM = 100.0;

    public MaterialResponse save(MaterialRequest materialRequest) {
        log.info("Creating new material.");
        try {
            if(materialRequest.getWeight() >= MINIMUM && materialRequest.getWeight() <= MAXIMUM) {
                Link link = linkTo(methodOn(MaterialController.class).save(materialRequest)).withSelfRel();
                MaterialResponse materialResponse = MaterialResponse.materialToResponse(
                        materialRepository.save(materialRequest.requestToMaterial()));
                materialResponse.add(link);
                return materialResponse;
            } else {
                throw new DuplicateRequestException("Invalid weight");
            }
        } catch (DuplicateRequestException ex) {
            throw new DuplicateRequestException(ex.getMessage());
        }
    }



    public List<MaterialResponse> getAll() {
        log.info("Getting all materials.");
        try {
            List<MaterialResponse> materialResponseList = new ArrayList<>();
            materialRepository.findAll().forEach(m -> materialResponseList.add(MaterialResponse.materialToResponse(m)));

            for (final MaterialResponse materialResponse : materialResponseList) {
                String materialResponseId = materialResponse.getResponseId();
                Link selfLink = linkTo(methodOn(MaterialController.class).getById(materialResponseId)).withSelfRel();
                materialResponse.add(selfLink);
            }

            return materialResponseList;
        } catch (MongoWriteConcernException ex) {
            throw ex;
        }
    }

    public MaterialResponse getById(String id) {
        log.info("Getting material with the id: " + id);
        Material material = materialRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        MaterialResponse materialResponse = MaterialResponse.materialToResponse(material);
        Link link = linkTo(methodOn(MaterialController.class).getById(id)).withSelfRel();
        materialResponse.add(link);
        return materialResponse;
    }

    public MaterialResponse delete(String id) {
        log.info("Deleting material with the id: " + id);
        Material material = materialRepository.findById(id).orElseThrow(() -> new DeleteBadRequestException(id));
        materialRepository.delete(material);

        MaterialResponse materialResponse = MaterialResponse.materialToResponse(material);
        Link link = linkTo(methodOn(MaterialController.class).delete(id)).withSelfRel();
        materialResponse.add(link);

        return materialResponse;
    }

    @Transactional
    public MaterialResponse update(MaterialRequest materialRequest, String id) {
        log.info("Updating material with the id: " + id);
        materialRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        Material material = materialRequest.requestToMaterial();
        material.setId(id);
        MaterialResponse materialResponse = MaterialResponse.materialToResponse(materialRepository.save(material));
        Link link = linkTo(methodOn(MaterialController.class).update(materialRequest, id)).withSelfRel();
        materialResponse.add(link);
        return materialResponse;
    }

}
