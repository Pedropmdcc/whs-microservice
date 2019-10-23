package com.whs.warehouse;

import com.whs.warehouse.infrastructure.material.model.Material;
import com.whs.warehouse.infrastructure.material.repository.MaterialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MigrationTest {

    @Autowired
    private MaterialRepository materialRepository;

    @Test
    public void insertMaterialTest() {
        Material material = new Material("seats", "i3");
        materialRepository.save(material);
    }
}
