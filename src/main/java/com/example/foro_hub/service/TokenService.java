package com.example.foro_hub.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private static final String SECRET_KEY = "mysecretkey";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 días

    public String generateToken(Authentication authentication) {
        Object SignatureAlgorithm = null;
        return RSocketSecurity.JwtSpec.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}