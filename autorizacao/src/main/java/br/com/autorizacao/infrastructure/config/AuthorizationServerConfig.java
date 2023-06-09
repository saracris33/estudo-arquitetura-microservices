package br.com.autorizacao.infrastructure.config;

import java.time.Duration;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthorizationServerConfig {
	
	@Bean 
	@Order(1)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
			throws Exception {
		/*
		 * APLICA CONFIGURACAO PADRAO DE SEGURANCA DO SPRING
		 */
		OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
		authorizationServerConfigurer.oidc(Customizer.withDefaults()); // Habilita OpenID Connect 1.0
		http.apply(authorizationServerConfigurer);
		/*authorizationServerConfigurer.oidc(oidc ->
							oidc.logoutEndpoint(logoutEndpoint ->
								logoutEndpoint.logoutRequestConverter(logoutRequestConverter) 
										.logoutRequestConverters(logoutRequestConvertersConsumer)   
										.authenticationProvider(authenticationProvider) 
										.authenticationProviders(authenticationProvidersConsumer)   
										.logoutResponseHandler(logoutResponseHandler)   
										.errorResponseHandler(errorResponseHandler) 
						          )
					        );*/
		
		http.csrf(csrf -> csrf.disable());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		// Aceite tokens de acesso para informações do usuário e/ou registro do cliente
		http.oauth2ResourceServer((resourceServer) -> resourceServer
						.jwt(Customizer.withDefaults()));

		return http.build();
	}
	
	
	@Bean 
	@Order(2)
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
			throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.anyRequest().authenticated()
			);
			// Form login handles the redirect to the login page from the
			// authorization server filter chain
			//.formLogin(Customizer.withDefaults());

		return http.build();
	}
	
	
	/*
	 * EXEMPLO RECUPERACAO DE USUARIO PARA AUTENTICACAO FLUXO AUTHORIZATION-CODE
	 */
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails userDetails = User.builder()
				.username("Sara")
				.password(passwordEncoder.encode("senha123"))
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(userDetails);
	}
	
	
	/*
	 * GERENCIA REGISTRO DE CLIENTES
	 */
	@Bean
	public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {
        /*
         * CLIENT OAUTH 
         */
		RegisteredClient sdaGateway = RegisteredClient
				.withId("1")
				.clientId("gateway")
				.clientSecret(passwordEncoder.encode("cliente123"))
				.clientName("gateway-client")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.scope(OidcScopes.OPENID)
				.scope("read")
				.scope("write")
				.tokenSettings(TokenSettings.builder()
						.accessTokenTimeToLive(Duration.ofMinutes(30))
						//.reuseRefreshTokens(false)
						//.refreshTokenTimeToLive(Duration.ofDays(1))
						.build())

				.build();


		return new InMemoryRegisteredClientRepository(Arrays.asList(sdaGateway));
	}
	
	
	@Bean
	public AuthorizationServerSettings providerSettings(AutorizacaoProperties properties) {
		return AuthorizationServerSettings.builder()
				.issuer(properties.getProviderUrl())
				//.authorizationEndpoint("/api/oauth2/authorize")
				//.deviceAuthorizationEndpoint("/api/oauth2/device_authorization")
				//.deviceVerificationEndpoint("/api/oauth2/device_verification")
				//.tokenEndpoint("/api/oauth2/token")
				//.tokenIntrospectionEndpoint("/api/oauth2/introspect")
				//.tokenRevocationEndpoint("/api/oauth2/revoke")
				//.jwkSetEndpoint("/api/oauth2/jwks")
				//.oidcLogoutEndpoint("/api/connect/logout")
				//.oidcUserInfoEndpoint("/api/connect/userinfo")
				//.oidcClientRegistrationEndpoint("/api/connect/register")
				.build();
	}

}
