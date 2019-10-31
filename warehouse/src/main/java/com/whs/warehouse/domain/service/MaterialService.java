package com.whs.warehouse.domain.service;

import com.whs.warehouse.api.dto.MaterialDto;
import com.whs.warehouse.api.dto.MaterialResponseDto;
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

    public List<com.whs.warehouse.infrastructure.model.Material> list() {
        log.info("Listing all courses");
        return materialRepository.findAll();
    }

    public void add(MaterialDto materialDto) {
        materialRepository.save(materialDto.dtoToMaterial());
    }

    public List<MaterialResponseDto> getAll() {

        List<MaterialResponseDto> materialResponseDtoList = new ArrayList<>();
        materialRepository.findAll().forEach(e -> materialResponseDtoList.add(MaterialResponseDto.materialToDto(e)));
        return materialResponseDtoList;
    }

    public MaterialResponseDto getById(String id) {
        return MaterialResponseDto.materialToDto(materialRepository.findById(id).get());
    }
}
