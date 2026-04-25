package br.com.fatecmaua.projeto_nuvem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UsuarioConfig {
	
	@Bean
	public UserDetailsService usuario() {
		
		return new InMemoryUserDetailsManager(
				User
				.withUsername("fatec_dsm")
				.password("{noop}1234")
				.roles("USER").build());
	}
 
}
