package com.gsTech.um_para_um.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gsTech.um_para_um.orm.Holder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;


    public String generateToken(Holder holder) {

        return JWT.create()
                .withIssuer("um-para-um-API")
                .withSubject(holder.getUsername())
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plusSeconds(this.expiration)))
                .sign(Algorithm.HMAC256(this.secret));
    }


    public boolean validateToken(String token) {

        try {
            JWT.require(Algorithm.HMAC256(this.secret))
                    .withIssuer("um-para-um-API")
                    .build()
                    .verify(token);
            return true;

        }catch (JWTVerificationException e) {

            return false;
        }
    }

    // extrair apenas o username do Token
    public String getUserNameFromToken(String token) {

        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }


}
