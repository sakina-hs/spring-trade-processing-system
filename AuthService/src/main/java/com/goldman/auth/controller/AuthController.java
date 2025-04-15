package com.goldman.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldman.auth.entity.TradeUser;
import com.goldman.auth.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "Login endpoint";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody TradeUser user) {
        return userService.registerUser(user);

    }

    @RequestMapping("/logout")
    public String logout() {
        return "Logout endpoint";
    }
}