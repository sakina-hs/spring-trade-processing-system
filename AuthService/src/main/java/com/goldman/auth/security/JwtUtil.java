package com.goldman.auth.security;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

import java.util.Date;

@Component
public class JwtUtil {

    /*
     * private final String secret = "secretKey";
     * 
     * public String generateToken(String username) {
     * return Jwts.builder()
     * .setSubject(username)
     * .setIssuedAt(new Date())
     * .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
     * .signWith(SignatureAlgorithm.HS256, secret)
     * .compact();
     * }
     * 
     * public String extractUsername(String token) {
     * return extractClaims(token).getSubject();
     * }
     * 
     * private Claims extractClaims(String token) {
     * return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
     * }
     */
}