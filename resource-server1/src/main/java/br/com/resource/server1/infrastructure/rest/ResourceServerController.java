package br.com.resource.server1.infrastructure.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class ResourceServerController {
	
	@GetMapping("/test")
	public String bemVindo(@AuthenticationPrincipal Jwt jwt) {
		return String.format("Bem vindo ao Resource Server 1 - token client %s (sub)", jwt.getSubject());
	}
	
	@GetMapping("/oidc-principal")
	public OidcUser getOidcUserPrincipal(
	  @AuthenticationPrincipal OidcUser principal) {
	    return principal;
	}

}
