package com.whs.registry.serviceController;

import org.springframework.web.bind.annotation.RequestMapping;

public interface RegistryInstanceRestController {
        @RequestMapping("/registry")
        String registry();
}
