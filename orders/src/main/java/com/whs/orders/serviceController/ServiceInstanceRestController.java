package com.whs.orders.serviceController;

import org.springframework.web.bind.annotation.RequestMapping;

public interface ServiceInstanceRestController {
        @RequestMapping("/order")
        String order();
}
