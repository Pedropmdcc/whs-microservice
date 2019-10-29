package com.whs.warehouse.domain.service;

import com.whs.warehouse.infrastructure.model.Material;
import com.whs.warehouse.infrastructure.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
