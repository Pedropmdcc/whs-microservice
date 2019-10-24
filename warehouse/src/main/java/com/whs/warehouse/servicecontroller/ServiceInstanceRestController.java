package com.whs.warehouse.servicecontroller;

import org.springframework.web.bind.annotation.RequestMapping;

public interface ServiceInstanceRestController {
    @RequestMapping("/warehouse")
    String warehouse();
}
