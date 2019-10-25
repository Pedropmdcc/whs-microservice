package com.whs.registry;

import com.whs.registry.message.Producer;
import com.whs.registry.serviceController.RegistryInstanceRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication implements RegistryInstanceRestController {

    public static void main(String[] args) {
        SpringApplication.run(RegistryApplication.class, args);
    }

    @Autowired
    Producer producer;

    @Override
    public String registry() {
        producer.produceMsg("registry");
        return String.format("Test from '%s'!");
    }
}
