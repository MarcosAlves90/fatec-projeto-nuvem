package br.com.fatecmaua.projeto_nuvem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SegurancaConfig {

    private final JWTFiltroAutenticacao jwtFiltroAutenticacao;

    public SegurancaConfig(JWTFiltroAutenticacao jwtFiltroAutenticacao) {
        this.jwtFiltroAutenticacao = jwtFiltroAutenticacao;
    }

    @Bean
    public SecurityFilterChain filtrar(HttpSecurity httpRequest) throws Exception {

        httpRequest
                .csrf(csrf -> csrf.disable())
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(request -> request
                        // .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/autenticacao/**").permitAll()
                        .anyRequest().authenticated());

        return httpRequest.build();

    }

}
