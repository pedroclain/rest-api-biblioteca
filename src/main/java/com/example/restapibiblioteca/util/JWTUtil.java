package com.example.restapibiblioteca.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private final String SECRET = "secret";
    private final String TOKEN_PREFIX = "Bearer ";
    private final long EXP_TIME = 360000L;

    public String generateToken(Usuario usuario) {
        return Jwts.builder().setSubject(usuario.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXP_TIME))
                .signWith(SignatureAlgorithm.HS256 ,SECRET)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
