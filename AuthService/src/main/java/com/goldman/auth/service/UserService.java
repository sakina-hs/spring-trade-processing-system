package com.goldman.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.goldman.auth.security.JwtUtil;

import java.util.Optional;

import javax.validation.Valid;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

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

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user);
        logger.info("Generated JWT token: {}", token);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}