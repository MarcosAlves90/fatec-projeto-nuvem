package br.com.fatecmaua.projeto_nuvem.control;

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

    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AutenticacaoController(JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String realizarLogin(@RequestParam String username, @RequestParam String password, @RequestParam(value = "duracao", defaultValue = "15") Integer duracao) {
        try {

            var autenticacao = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(autenticacao);
            return jwtUtil.gerarToken(username, duracao);

        } catch (Exception e) {
            return "Credenciais inválidas";
        }
    }

}
