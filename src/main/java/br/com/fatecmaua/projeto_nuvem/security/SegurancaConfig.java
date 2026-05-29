package br.com.fatecmaua.projeto_nuvem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SegurancaConfig {
	
	@Autowired
	private JWTFiltroAutenticacao filtro;
	
	@Bean
	public SecurityFilterChain filtrar(HttpSecurity httpRequest) throws Exception {
		
		httpRequest.csrf(csrf -> csrf.disable())
				   .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
				   .authorizeHttpRequests(request -> request.requestMatchers("/autenticacao/**").permitAll()
						                                    .anyRequest().authenticated())
				   .addFilterBefore(filtro, UsernamePasswordAuthenticationFilter.class)
				   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
					
		return httpRequest.build();
	}

}
