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


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // verifica se o header existe e comeca com bearer
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // extrai o token do header
        String token = header.substring(7);


        try {
            String username = jwtTokenUtil.getUserNameFromToken(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // verifica se o token e valido antes de authenticar
                if (jwtTokenUtil.validateToken(token)) {

                    UserDetails user = holderService.loadUserByUsername(username);
                    var authenticated = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticated);
                }
            }

        } catch (Exception e) {
            logger.warn("Erro ao processar o JWT: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
