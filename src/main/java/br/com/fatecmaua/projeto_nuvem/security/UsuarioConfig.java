package br.com.fatecmaua.projeto_nuvem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fatecmaua.projeto_nuvem.model.Usuario;
import br.com.fatecmaua.projeto_nuvem.repository.UsuarioRepository;

@Configuration
public class UsuarioConfig {
	
	@Autowired
	private UsuarioRepository repU;
	
	@Bean
	public UserDetailsService fornecerUsuario() {
		
		return email -> { 
			
			Usuario usuario = repU.findByEmail(email)
			.orElseThrow(() -> 
			new UsernameNotFoundException("Usuário não cadastrado"));
			
			return User.builder()
					.username(usuario.getEmail())
					.password(usuario.getSenha())
					.roles(usuario.getRole())
					.build();
			
		};
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
