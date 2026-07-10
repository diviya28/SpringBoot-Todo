package com.diviya.todo.util;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "mysecretkeymysecretkeymysecretkey12";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours
    private final Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String email){
        return Jwts.builder()
                 .setSubject(email)
                 .setIssuedAt(new Date())
                 .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                 .signWith(secretKey,SignatureAlgorithm.HS256)
                 .compact();
    }

    public boolean validateJwtToken(String token){
        try{
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

            return true;
        }
        catch(JwtException exception){
            return false;
        }
    }
}
