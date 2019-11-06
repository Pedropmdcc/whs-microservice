package com.whs.warehouse.api.impl;

import com.whs.warehouse.api.controller.MaterialController;
import com.whs.warehouse.api.dto.request.MaterialRequest;
import com.whs.warehouse.api.dto.response.MaterialResponse;
import com.whs.warehouse.domain.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
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
    public Resource<MaterialResponse> add(@RequestBody MaterialRequest materialRequest, UriComponentsBuilder builder) {
        MaterialResponse materialResponse = materialService.add(materialRequest);
        Link link = linkTo(methodOn(MaterialController.class).add(materialRequest,builder)).withSelfRel();
        materialResponse.add(link);
        Resource<MaterialResponse> result = new Resource<>(materialResponse, link);
        return result;
    }

    @Override
    public Resources<MaterialResponse> getAll() {
        List<MaterialResponse> materialList = materialService.getAll();

        for(final MaterialResponse materialResponse : materialList) {
            String materialResponseId = materialResponse.getResponseId();
            Link selfLink = linkTo(methodOn(MaterialController.class).getById(materialResponseId)).withSelfRel();
            materialResponse.add(selfLink);
        }

        Link link = linkTo(methodOn(MaterialController.class).getAll()).withSelfRel();
        Resources<MaterialResponse> result = new Resources<>(materialList, link);
        return result;
    }

    @Override
    public Resource<MaterialResponse> getById(String id) {
        MaterialResponse materialResponse = materialService.getById(id);
        Link link = linkTo(methodOn(MaterialController.class).getById(id)).withSelfRel();
        materialResponse.add(link);
        Resource<MaterialResponse> result = new Resource<>(materialResponse, link);
        return result;
    }

    @Override
    public Resource<MaterialResponse> delete(String id) {
        MaterialResponse materialResponse = materialService.delete(id);
        Link link = linkTo(methodOn(MaterialController.class).delete(id)).withSelfRel();
        materialResponse.add(link);
        Resource<MaterialResponse> result = new Resource<>(materialResponse, link);
        return result;
    }

    @Override
    public Resource<MaterialResponse> update(MaterialRequest materialRequest, String id) {
        MaterialResponse materialResponse = materialService.update(materialRequest, id);
        Link link = linkTo(methodOn(MaterialController.class).update(materialRequest, id)).withSelfRel();
        materialResponse.add(link);
        Resource<MaterialResponse> result = new Resource<>(materialResponse, link);
        return result;
    }
}
