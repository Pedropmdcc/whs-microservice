package com.whs.warehouse;

import com.whs.warehouse.infrastructure.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MigrationTest {

    @Autowired
    private MaterialRepository materialRepository;

    /*@Test
    public void insertMaterialTest() {
        Material material = new Material("seats", "i3");
        materialRepository.save(material);
    }*/
}
