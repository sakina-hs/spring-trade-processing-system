package com.goldman.auth.security;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired // Inject UserDetailsService
    private UserDetailsService userDetailsService;

    // private final String[] freeResourceUrls = { "/swagger-ui.html",
    // "/swagger-ui/**", "/v3/api-docs/**",
    // "/swagger-resources/**", "/api-docs/**", "/aggregate/**",
    // "/actuator/prometheus", "/auth/register",
    // "/auth/login" };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // String requestPath = request.getServletPath();

        // Allow free resource URLs to pass through without JWT validation
        // if (Arrays.asList(freeResourceUrls).contains(requestPath) ||
        // Arrays.stream(freeResourceUrls)
        // .filter(url -> url.endsWith("/**"))
        // .anyMatch(url -> requestPath.startsWith(url.substring(0, url.length() - 3))))
        // {
        // filterChain.doFilter(request, response);
        // return;
        // }

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Let other filters handle unauthenticated requests
            return;
        }

        jwt = authHeader.substring(7);
        username = jwtUtil.extractUsername(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // Use injected
                                                                                            // UserDetailsService

            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response); // Continue the filter chain for authenticated requests
    }
}