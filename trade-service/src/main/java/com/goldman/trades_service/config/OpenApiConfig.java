package com.goldman.trades_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI tradeServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Trade Service API")
                        .description("Trade Service for Project")
                        .version("1.0"));
    }
}