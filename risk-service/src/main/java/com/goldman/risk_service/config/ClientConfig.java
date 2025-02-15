package com.goldman.risk_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.beans.factory.annotation.Value;
import com.goldman.risk_service.client.SettlementServiceClient;

@Configuration
public class ClientConfig {

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