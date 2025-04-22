package com.goldman.api_gateway.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.List;
import java.security.Key;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return (String) claims.get("roles");
    }

    // Validate the JWT token
    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // Use the same secret key to parse the token
                    .build()
                    .parseClaimsJws(token); // This will throw an exception if the token is invalid
            return true; // Token is valid
        } catch (Exception e) {
            return false; // Token is invalid (expired or tampered)
        }
    }

    // Extract user details from the token and create an Authentication object
    /*
     * public Authentication getAuthentication(String token) {
     * Claims claims = Jwts.parserBuilder()
     * .setSigningKey(getSigningKey())
     * .build()
     * .parseClaimsJws(token)
     * .getBody();
     * String username = claims.getSubject();
     * // Extract roles from the token and map them to authorities
     * List<SimpleGrantedAuthority> authorities = ((List<?>)
     * claims.get("roles")).stream()
     * .map(role -> new SimpleGrantedAuthority((String) role))
     * .collect(Collectors.toList());
     * // Create a User object and set the roles
     * User principal = new User(username, "", authorities);
     * // Return an Authentication object
     * return new UsernamePasswordAuthenticationToken(principal, token,
     * authorities);
     * }
     */

    // Extract the username from the token
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

}
