package br.com.autorizacao.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@Validated
@ConfigurationProperties("autorizacao.auth")
public class AutorizacaoProperties {

	
	@NotBlank
	private String providerUrl;
	
	
}
