package com.goldman.auth.service;

import com.goldman.auth.entity.TradeUser; // Assuming you have a User entity
import com.goldman.auth.repository.UserRepository; // Assuming you have a UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TradeUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user));
    }

    // Helper method to extract authorities/roles from your User entity
    private List<SimpleGrantedAuthority> getAuthorities(TradeUser user) {

        String role = "ROLE_" + user.getRole().toUpperCase(); // "trader" → "ROLE_TRADER"
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
        return authorities;
    }
}