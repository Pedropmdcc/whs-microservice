package com.whs.orders;

import com.netflix.discovery.EurekaClient;
import com.whs.orders.serviceController.ServiceInstanceRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OrdersApplication implements ServiceInstanceRestController {

	@Qualifier("eurekaClient")
	@Autowired
	@Lazy
	private EurekaClient eurekaClient;

	@Value("${spring.application.name}")
	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}

	@Override
	public String order() {
		return String.format(
				"Test from '%s'!", eurekaClient.getApplication(appName).getName());
	}
}
