package com.goldman.api_gateway.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.goldman.api_gateway.security.JwtAuthFilter;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
        /*
         * @Autowired
         * private JwtAuthFilter jwtAuthFilter;
         * 
         * private final String[] freeResourceUrls = { "/swagger-ui.html",
         * "/swagger-ui/**", "/v3/api-docs/**",
         * "/swagger-resources/**", "/api-docs/**", "/aggregate/**",
         * "/actuator/prometheus",
         * "/auth/register",
         * "/auth/login", "/actuator/**", "/trades/*", "/funds/*", "/risk/*",
         * "/notification/*",
         * "/settlement/*" };
         * 
         * @Bean
         * public CorsConfigurationSource corsConfigurationSource() {
         * CorsConfiguration config = new CorsConfiguration();
         * config.setAllowedOrigins(List.of("http://localhost:5173")); // Ensure only
         * one origin is allowed
         * config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
         * config.setAllowedHeaders(List.of("*"));
         * config.setAllowCredentials(true); // Allow credentials for cookies/auth
         * headers
         * 
         * UrlBasedCorsConfigurationSource source = new
         * UrlBasedCorsConfigurationSource();
         * source.registerCorsConfiguration("/**", config);
         * return source;
         * }
         * 
         * @Bean
         * public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity
         * httpSecurity) throws Exception {
         * return httpSecurity
         * .csrf(csrf -> csrf.disable())
         * .cors(cors -> cors.configurationSource(corsConfigurationSource()))
         * .authorizeExchange(auth -> auth
         * .pathMatchers(freeResourceUrls).permitAll()
         * .anyExchange().authenticated())
         * .addFilterAt(jwtAuthFilter, SecurityWebFiltersOrder.AUTHENTICATION)
         * .build();
         * }
         */
}
