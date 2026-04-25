package br.com.fatecmaua.projeto_nuvem.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFiltroAutenticacao extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JWTFiltroAutenticacao(JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);
            String username = jwtUtil.extrairUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails usuario = userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validarToken(token)) {
                    
                    var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(autenticacao);
                    
                }

            }

        }

        filterChain.doFilter(request, response);

    }

}
