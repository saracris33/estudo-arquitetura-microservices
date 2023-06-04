package br.com.autorizacao.infrastructure.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/")
@RestController
public class AutorizacaoResource {
	
	@GetMapping("test")
	public String bemVindo() {
		return String.format("Bem vindo ao Authorization Server - Servidor de Autorização!");
	}

}
