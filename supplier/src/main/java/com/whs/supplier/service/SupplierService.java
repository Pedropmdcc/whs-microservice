package com.whs.supplier.service;

import com.whs.supplier.api.dto.SupplierDto;
import com.whs.supplier.infrastructure.model.Supplier;
import com.whs.supplier.infrastructure.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplierService {

    @Autowired
    private final SupplierRepository supplierRepository;

    public List<Supplier> list() {
        log.info("Listing all courses");
        return supplierRepository.findAll();
    }

    public void insert(SupplierDto dto){
        log.info("Inserting new supplier" + dto.getName() + "in the database.");
        supplierRepository.insert(dto.dtoToSupplier());
    }
}
