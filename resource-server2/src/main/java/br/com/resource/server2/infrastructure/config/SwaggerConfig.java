package br.com.resource.server2.infrastructure.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
	
	private static final String URL_RESOURCE_SERVER = "http://localhost:8084";
	private static final String URL_RESOURCE_SERVER_NO_GATEWAY = "http://localhost:8080/resource-server2";


	@Bean
    OpenAPI infoOpenAPI() {
		 final  String  securitySchemeName  =  "bearerAuth" ; 
		 
	    return new OpenAPI()
	    	.info(new Info().title("Resource server 02")
	    	.description("Documentação Servidor de recurso 02")
	    	.version("v1"))
	    	.servers(List.of(new Server().url(URL_RESOURCE_SERVER_NO_GATEWAY),
	    			         new Server().url(URL_RESOURCE_SERVER)))
	        .components(obterComponenteSecurityBearer(securitySchemeName))
	            .security(List.of(new SecurityRequirement().addList(securitySchemeName)));
	}
	
	
	private Components obterComponenteSecurityBearer(final String securitySchemeName) {
		return new Components()
		    .addSecuritySchemes(
		        securitySchemeName,
		        new SecurityScheme()
		            .type(SecurityScheme.Type.HTTP)
		            .scheme("bearer")
		            .bearerFormat("JWT"));
	}

}
