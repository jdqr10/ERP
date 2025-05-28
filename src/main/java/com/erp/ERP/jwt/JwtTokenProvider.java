package com.erp.ERP.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenProvider {

    

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    // key for signing the JWT
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // 1. Generate token
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 2. get username from token
    public String getUsernameFromJWT(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 3. token validation
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            System.err.println("Token JWT inválido: " + ex.getMessage());
        }
        return false;
    }
}
