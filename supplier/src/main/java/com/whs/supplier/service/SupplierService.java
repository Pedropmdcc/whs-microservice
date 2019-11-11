package com.whs.supplier.service;

import com.mongodb.MongoWriteConcernException;
import com.whs.supplier.api.dto.errors.DeleteBadRequestException;
import com.whs.supplier.api.dto.errors.DuplicateRequestException;
import com.whs.supplier.api.dto.errors.NotFoundException;
import com.whs.supplier.api.dto.request.SupplierRequest;
import com.whs.supplier.api.dto.response.SupplierResponse;
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

    public List<Supplier> listSuppliers() {
        log.info("Listing all courses");
        return supplierRepository.findAll();
    }

    public SupplierResponse saveSupplier(SupplierRequest dto){
        log.info("Inserting new supplier" + dto.getName() + " in the database.");
        try {
            return SupplierResponse.supplierToDto(supplierRepository.insert(dto.dtoToSupplier()));
        } catch (Exception ex){
            throw new DuplicateRequestException(ex.getMessage());
        }
    }

    public SupplierResponse updateSupplier(SupplierRequest dto, String id){
        log.info("Updating supplier: " + dto.getName() + " in the database.");
        try {
            Supplier supplier = dto.dtoToSupplier();
            supplier.setId(id);
            return SupplierResponse.supplierToDto(supplierRepository.save(supplier));
        } catch (Exception ex){
            throw new DuplicateRequestException(ex.getMessage());
        }
    }

    public SupplierResponse deleteSupplier(String id){
        log.info("Deleting supplier with id: " + id + " from the database.");
        try {
            Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new DeleteBadRequestException(id));
            supplierRepository.delete(supplier);
            return SupplierResponse.supplierToDto(supplier);
        } catch (MongoWriteConcernException e){
            throw e;
        }
    }

    public SupplierResponse findSupplier(String id){
        log.info("Finding supplier: " + id + " in the database.");
        try {
            Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
            return SupplierResponse.supplierToDto(supplier);
        } catch (NullPointerException e){
            return null;
        }
    }
}
