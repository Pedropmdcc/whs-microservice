package com.whs.supplier.servicecontroller;

import org.springframework.web.bind.annotation.RequestMapping;

public interface ServiceInstanceRestController {
    @RequestMapping("/supplier")
    String supplier();
}
