package com.goldman.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldman.auth.dto.AuthResponse;
import com.goldman.auth.dto.LoginRequest;
import com.goldman.auth.entity.TradeUser;
import com.goldman.auth.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.validateLogin(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid TradeUser user) {
        return userService.registerUser(user);

    }

    @RequestMapping("/logout")
    public String logout() {
        return "Logout endpoint";
    }
}