package com.whs.supplier.service;

import com.mongodb.MongoSocketWriteException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.ServerAddress;
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

    public List<Supplier> listSuppliers() {
        log.info("Listing all courses");
        return supplierRepository.findAll();
    }

    public Supplier saveSupplier(SupplierDto dto){
        log.info("Inserting new supplier" + dto.getName() + "in the database.");
        try {
            return supplierRepository.save(dto.dtoToSupplier());
        } catch (MongoWriteConcernException e){
            throw e;
        }
    }

    public void cleanSuppliers(){
        log.info("Cleaning repository");
        try {
            supplierRepository.deleteAll();
        } catch (MongoWriteConcernException e){
            throw e;
        }
    }

    public void deleteSupplier(String id){
        log.info("Deleting supplier with id: " + id + "from the database.");
        try {
            supplierRepository.deleteById(id);
        } catch (MongoWriteConcernException e){
            throw e;
        }
    }

    public Supplier updateSupplier(SupplierDto dto, String id){
        log.info("Updating supplier: " + dto.getName() + "from the database.");
        try {
            Supplier supplier = dto.dtoToSupplier();
            supplier.setId(id);
            return supplierRepository.save(supplier);
        } catch (MongoWriteConcernException e){
            throw e;
        }
    }
}
