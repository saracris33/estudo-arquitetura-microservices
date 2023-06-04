package br.com.resource.server2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ResourceServer2Application {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServer2Application.class, args);
	}

}
