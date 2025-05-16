package com.goldman.trades_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.goldman.trades_service.client.SettlementServiceClient;

import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RestClientConfig {

        @Bean
        public SettlementServiceClient settlementServiceClient(
                        @Value("${settlement.service.url}") String baseUrl) {
                RestClient restClient = RestClient.builder()
                                .baseUrl(baseUrl)
                                .build();

                var adapter = RestClientAdapter.create(restClient);
                return HttpServiceProxyFactory.builder()
                                .exchangeAdapter(adapter)
                                .build()
                                .createClient(SettlementServiceClient.class);
        }
}