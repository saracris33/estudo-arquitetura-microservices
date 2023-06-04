package br.com.gateway.infrastructure.config;

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
	

	private static final String URL_GATEWAY = "http://localhost:8080";


	@Bean
    OpenAPI infoOpenAPI() {
		 final  String  securitySchemeName  =  "bearerAuth" ; 
		 
	    return new OpenAPI()
	    	.info(new Info().title("Gateway")
	    	.description("Documentação do Gateway ponto de entrada unico para os microserviços")
	    	.version("v1"))
	    	.servers(List.of(new Server().url(URL_GATEWAY)))
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
