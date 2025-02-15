package com.goldman.api_gateway.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class Route {

        @Value("${trade.service.url}")
        private String tradeServiceUrl;
        @Value("${risk.service.url}")
        private String riskServiceUrl;
        @Value("${settlement.service.url}")
        private String settlementServiceUrl;

        @Bean
        public RouterFunction<ServerResponse> tradeServiceRoute() {
                return GatewayRouterFunctions.route("trade_service")
                                .route(RequestPredicates.path("/api/trade"), HandlerFunctions.http(tradeServiceUrl))
                                .filter(CircuitBreakerFilterFunctions.circuitBreaker("tradeServiceCircuitBreaker",
                                                URI.create("forward:/fallbackRoute")))
                                .build();
        }

        @Bean
        public RouterFunction<ServerResponse> tradeServiceSwaggerRoute() {
                return GatewayRouterFunctions.route("trade_service_swagger")
                                .route(RequestPredicates.path("/aggregate/trade_service/v3/api_docs"),
                                                HandlerFunctions.http(tradeServiceUrl))
                                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
                                                "tradeServiceSwaggerCircuitBreaker",
                                                URI.create("forward:/fallbackRoute")))
                                .filter(setPath("/api_docs"))
                                .build();
        }

        @Bean
        public RouterFunction<ServerResponse> riskServiceRoute() {
                return GatewayRouterFunctions.route("risk_service")
                                .route(RequestPredicates.path("/api/risk"), HandlerFunctions.http(riskServiceUrl))
                                .filter(CircuitBreakerFilterFunctions.circuitBreaker("riskServiceCircuitBreaker",
                                                URI.create("forward:/fallbackRoute")))
                                .build();
        }

        @Bean
        public RouterFunction<ServerResponse> riskServiceSwaggerRoute() {
                return GatewayRouterFunctions.route("risk_service_swagger")
                                .route(RequestPredicates.path("/aggregate/risk_service/v3/api_docs"),
                                                HandlerFunctions.http(riskServiceUrl))
                                .filter(CircuitBreakerFilterFunctions.circuitBreaker("riskServiceSwaggerCircuitBreaker",
                                                URI.create("forward:/fallbackRoute")))
                                .filter(setPath("/api_docs"))
                                .build();
        }

        @Bean
        public RouterFunction<ServerResponse> settlementServiceRoute() {
                return GatewayRouterFunctions.route("settlement_service")
                                .route(RequestPredicates.path("/api/settlement"),
                                                HandlerFunctions.http(settlementServiceUrl))
                                .filter(CircuitBreakerFilterFunctions.circuitBreaker("settlementServiceCircuitBreaker",
                                                URI.create("forward:/fallbackRoute")))
                                .build();
        }

        @Bean
        public RouterFunction<ServerResponse> settlementServiceSwaggerRoute() {
                return GatewayRouterFunctions.route("settlement_service_swagger")
                                .route(RequestPredicates.path("/aggregate/settlement_service/v3/api_docs"),
                                                HandlerFunctions.http(settlementServiceUrl))
                                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
                                                "settlementServiceSwaggerCircuitBreaker",
                                                URI.create("forward:/fallbackRoute")))
                                .filter(setPath("/api_docs"))
                                .build();
        }

        @Bean
        public RouterFunction<ServerResponse> fallbackRoute() {
                return route("fallbackRoute")
                                .GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                                                .body("Service Unavailable, please try again later"))
                                .build();
        }
}