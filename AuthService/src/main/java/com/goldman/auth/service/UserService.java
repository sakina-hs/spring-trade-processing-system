package com.goldman.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.goldman.auth.dto.AuthResponse;
import com.goldman.auth.dto.LoginRequest;
import com.goldman.auth.entity.TradeUser;
import com.goldman.auth.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public ResponseEntity<String> registerUser(TradeUser user) {
        // Check if the user already exists
        Optional<TradeUser> existingUser = userRepository.findByEmail(user.getEmail());
        ResponseEntity<String> response;

        if (existingUser.isPresent()) {
            response = ResponseEntity.badRequest().body("User already exists");
        } else {
            // Encode the password before saving
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            response = ResponseEntity.ok("User registered successfully");
        }

        return response;
    }

    public ResponseEntity<AuthResponse> validateLogin(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        // Cast to UserDetails instead of TradeUser
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails == null) {
            logger.error("Authentication failed for user: {}", loginRequest.getUsername());
            return ResponseEntity.status(401).body(null);
        }

        // Map UserDetails to TradeUser
        Optional<TradeUser> optionalTradeUser = userRepository.findByUsername(userDetails.getUsername());
        if (optionalTradeUser.isEmpty()) {
            logger.error("No TradeUser found for authenticated user: {}", userDetails.getUsername());
            return ResponseEntity.status(401).body(null);
        }

        TradeUser tradeUser = optionalTradeUser.get();
        logger.info("Authentication successful for user: {}", loginRequest.getUsername());
        String token = generateToken(tradeUser);
        logger.info("Generated JWT token: {}", token);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Value("${jwt.secret}")
    private String secret;

    private final long expirationMs = 1000 * 60 * 60 * 10; // 10 hours

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(TradeUser userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}