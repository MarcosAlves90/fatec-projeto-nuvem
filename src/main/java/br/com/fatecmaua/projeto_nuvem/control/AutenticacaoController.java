package br.com.fatecmaua.projeto_nuvem.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecmaua.projeto_nuvem.security.JWTUtil;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping("/login")
	public String realizarLogin(@RequestParam String username, 
			                    @RequestParam String password,
			                    @RequestParam(value = "duracao", defaultValue = "15") Integer duracao) {
		
		try {
			var autenticacao = new UsernamePasswordAuthenticationToken(username,password);
			manager.authenticate(autenticacao);
			return jwtUtil.gerarToken(username, duracao);	
		} catch(Exception e) {
			return "Credenciais inválidas!";
		}
		
		
	}
	

}
