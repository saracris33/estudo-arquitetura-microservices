package br.com.gateway.infrastructure.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class GatewayResource {
	
	@GetMapping("/test")
	public String bemVindo() {
		return String.format("Bem vindo ao Gateway! ");
	}

	@GetMapping("/oidc-principal")
	public OidcUser getOidcUserPrincipal(
	  @AuthenticationPrincipal OidcUser principal) {
	    return principal;
	}
	
	
	@GetMapping("/login/oauth2/code/{registrationId}")
	public String bemVindoAutenticado() {
		return String.format("Bem vindo ao Gateway voce está autenticado! ");
	}

}
