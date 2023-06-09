package br.com.autorizacao.infrastructure.config;



import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CorsGlobalConfiguration {
	
	@Bean
    CorsWebFilter CorsConfiguration() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));

		UrlBasedCorsConfigurationSource  source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		log.info("Configurando CORS Autorizacao");
		return new CorsWebFilter(source);
	}

}
