package com.whs.warehouse.domain.service;

import com.mongodb.MongoWriteConcernException;
import com.whs.warehouse.api.controller.MaterialController;
import com.whs.warehouse.api.dto.errors.DeleteBadRequestException;
import com.whs.warehouse.api.dto.errors.DuplicateRequestException;
import com.whs.warehouse.api.dto.errors.NotFoundException;
import com.whs.warehouse.api.dto.errors.WeightLimitsException;
import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import com.whs.warehouse.infrastructure.model.Material;
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

/**
 * Material Service
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final Double MINIMUM = 10.0;
    private final Double MAXIMUM = 100.0;

    /**
     * Saves an {@link Material} to the database.
     *
     * @param materialRequest the {@link MaterialRequest} to be saved.
     * @return {@link MaterialResponse} that was saved.
     */
    public MaterialResponse save(final MaterialRequest materialRequest) {
        log.info("Creating new material.");
        try {
            return this.saveConfirm(materialRequest);

        } catch (final DuplicateRequestException ex) {

            throw new DuplicateRequestException(ex.getMessage());

        }
    }

    /**
     * Get all Materials.
     * @return list of {@link MaterialResponse}
     */
    public List<MaterialResponse> getAll() {
        log.info("Getting all materials.");

        final List<MaterialResponse> materialResponseList = new ArrayList<>();
        this.materialRepository.findAll().forEach(m -> materialResponseList.add(MaterialResponse.materialToResponse(m)));

        for (final MaterialResponse materialResponse : materialResponseList) {
            final String materialResponseId = materialResponse.getResponseId();
            final Link selfLink = linkTo(methodOn(MaterialController.class).getById(materialResponseId)).withSelfRel();
            materialResponse.add(selfLink);
        }

        return materialResponseList;
    }

    /**
     * Get {@link MaterialResponse} from the requested Id.
     *
     * @param id The material request Id.
     * @return The MaterialResponse.
     */
    public MaterialResponse getById(final String id) {
        log.info("Getting material with the id: " + id);
        final Material material = this.materialRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        final MaterialResponse materialResponse = MaterialResponse.materialToResponse(material);
        final Link link = linkTo(methodOn(MaterialController.class).getById(id)).withSelfRel();
        materialResponse.add(link);
        return materialResponse;
    }

    /**
     * Delete an {@link Material} to the database.
     *
     * @param id The material request that will be deleted.
     */
    public MaterialResponse delete(final String id) {
        log.info("Deleting material with the id: " + id);
        final Material material = this.materialRepository.findById(id).orElseThrow(() -> new DeleteBadRequestException(id));
        this.materialRepository.delete(material);

        final MaterialResponse materialResponse = MaterialResponse.materialToResponse(material);
        //final Link link = linkTo(methodOn(MaterialController.class).delete(id)).withSelfRel();
        //materialResponse.add(link);

        return materialResponse;
    }

    /**
     * Update Material from the database
     * @param materialRequest Material Request object with data
     * @param id of the Material that will be update
     * @return MaterialResponse with updated data.
     */
    @Transactional
    public MaterialResponse update(final MaterialRequest materialRequest, final String id) {
        log.info("Updating material with the id: " + id);
        this.materialRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        final Material material = materialRequest.requestToMaterial();
        material.setId(id);
        final MaterialResponse materialResponse = MaterialResponse.materialToResponse(this.materialRepository.save(material));
        final Link link = linkTo(methodOn(MaterialController.class).update(materialRequest, id)).withSelfRel();
        materialResponse.add(link);
        return materialResponse;
    }

    private MaterialResponse saveConfirm(final MaterialRequest materialRequest) {
        if (materialRequest.getWeight() >= this.MINIMUM && materialRequest.getWeight() <= this.MAXIMUM) {
            final Link link = linkTo(methodOn(MaterialController.class).save(materialRequest)).withSelfRel();
            final MaterialResponse materialResponse = MaterialResponse.materialToResponse(
                    this.materialRepository.save(materialRequest.requestToMaterial()));
            materialResponse.add(link);
            return materialResponse;
        } else {
            throw new WeightLimitsException(materialRequest.getId());
        }
    }

}
