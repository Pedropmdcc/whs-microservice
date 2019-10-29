package com.whs.warehouse.api.controller;

import com.whs.warehouse.infrastructure.model.Material;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface MaterialController {

    @RequestMapping("/warehousetwo")
    String getAll();

    @RequestMapping("/warehousethree")
    List<Material> list();
}
