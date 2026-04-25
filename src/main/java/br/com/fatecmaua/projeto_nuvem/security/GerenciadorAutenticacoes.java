package br.com.fatecmaua.projeto_nuvem.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class GerenciadorAutenticacoes {

    public AuthenticationManager autenticar(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

}
