package com.erp.ERP.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import java.util.HashMap;
import java.util.Date;

import java.util.Map;

@Service
public class JwtService {
    
    private static final String SECRET_KEY = "8ce16d36cf9aeb7f89528928bb51cb4346a0983ff04647cb0597e90573990e140348c65a8f1f264915a7995f08e68ce562ae398bf6fa882f96e0774c8814474f2c4dfca19e3a282506cb34ad45117b4d4b440f921e8b02efb18e9610d98aed1899c5004c753264421439afba4d77dfd068afa201303378d8a1c262ca18e01edef915a9a79291ccd08a0370e8c7aa76836522b72ce2d7bce479c75ae099f38c51b19ae3bd457b48177bdcd7f83e87a0e5dcfc5b359d103ed1d61599c0abe50824b0709234ce63ca6c5be555e902da07dd52ac19692e7988318a9b90318584cafeab3243a03502605c791b06a7670067f33da36caca216bece0ba215687411e1a3";

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*64))
                .signWith(SignatureAlgorithm.HS256, getKey())
                .compact();

    }

    private SecretKey getKey() {
            byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
            return Keys.hmacShaKeyFor(keyBytes);
    }

}