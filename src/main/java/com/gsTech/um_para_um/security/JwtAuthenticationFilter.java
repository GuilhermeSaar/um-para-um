package com.gsTech.um_para_um.security;

import com.gsTech.um_para_um.service.HolderService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private HolderService holderService;


    // Ele intercepta as requisições HTTP para verificar a presença de um token JWT
    // Este é o método sobrescrito de um filtro personalizado, que vai manipular a requisição antes que ela chegue ao controlador
    @Override
    protected void doFilterInternal(HttpServletRequest request,   // requisicao HTTP
                                    HttpServletResponse response, // resposta HTTP
                                    FilterChain filterChain) throws ServletException, IOException {

        // O código pega o valor do cabeçalho Authorization da requisição. Esse cabeçalho deve conter o token JWT.
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // verifica se o header existe e comeca com bearer
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // extrai o token do header (cabecalho)
        String token = header.substring(7);


        try {
            // extrai o nome do usuario do token JWT
            String username = jwtTokenUtil.getUserNameFromToken(token);

            // Verifica se o usuario foi extraido com sucesso do token
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // verifica se o token e valido ou nao esta expirado antes de authenticar
                if (jwtTokenUtil.validateToken(token)) {

                    UserDetails user = holderService.loadUserByUsername(username);
                    var authenticated = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticated);
                }
            }

        } catch (Exception e) {
            logger.warn("Erro ao processar o JWT: " + e.getMessage());
        }

        filterChain.doFilter(request, response); // proximo filtro a ser executado apos esse filtro
    }
}
