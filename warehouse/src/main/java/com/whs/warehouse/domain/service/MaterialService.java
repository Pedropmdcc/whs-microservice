package com.whs.warehouse.domain.service;

import com.whs.warehouse.infrastructure.model.Material;
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

    public void add(Material material) {
        materialRepository.save(material);
    }

    public List<Material> getAll() {
        List<Material> materialList = new ArrayList<>();
        materialRepository.findAll().forEach(e -> materialList.add(e));
        return materialList;
    }

    public Material getById(String id) {
        return materialRepository.findById(id).get();
    }
}
