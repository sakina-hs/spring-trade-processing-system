package com.goldman.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.goldman.auth.entity.TradeUser;
import com.goldman.auth.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public ResponseEntity<String> registerUser(TradeUser user) {
        // Check if the user already exists
        Optional<TradeUser> existingUser = userRepository.findByEmail(user.getEmail());
        ResponseEntity<String> response;

        if (existingUser.isPresent()) {
            response = ResponseEntity.badRequest().body("User already exists");
        } else {
            userRepository.save(user);
            response = ResponseEntity.ok("User registered successfully");
        }

        return response;
    }
}