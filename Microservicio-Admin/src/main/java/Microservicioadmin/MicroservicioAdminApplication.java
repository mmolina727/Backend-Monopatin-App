package Microservicioadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.IOException;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioAdminApplication.class, args);
	}
}
