package br.com.resource.server1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class ResourceServer1Application {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServer1Application.class, args);
	}

}
