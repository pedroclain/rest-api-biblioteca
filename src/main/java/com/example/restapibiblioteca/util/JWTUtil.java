package com.example.restapibiblioteca.util;

import com.example.restapibiblioteca.domain.security.ApplicationUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private final String SECRET = "secret";
    private final String TOKEN_PREFIX = "Bearer ";
    private final long EXP_TIME = 1000 * 60 * 60 * 24;  // 1 day

    public String generateToken(ApplicationUser usuario) {
        return TOKEN_PREFIX + Jwts.builder().setSubject(usuario.getUsername())
                .setIssuer("com.example.restapibiblioteca")
                .setIssuedAt(new Date())
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

    public Date getExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody().getExpiration();
    }
}
