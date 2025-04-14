package com.goldman.trade_service.service;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.goldman.trade_service.model.TradeRequest;

@Service
public class TradeDataGenerater {

    @Autowired
    private WebClient webClient;

    private static final Logger logger = LoggerFactory.getLogger(TradeDataGenerater.class);
    private final List<String> symbols = List.of("AAPL", "GOOG", "TSLA", "MSFT", "AMZN");
    private final Random random = new Random();

    public ResponseEntity<String> sendMockTrades() {
        logger.info("inside");

        for (int i = 1; i <= 100; i++) {
            final int tradeId = i;// i becomes tradeId
            TradeRequest trade = new TradeRequest(
                    tradeId, // using i as tradeId (1 to 100)
                    symbols.get(random.nextInt(symbols.size())),
                    random.nextInt(100) + 1,
                    Math.round((100 + random.nextDouble() * 1000) * 100.0) / 100.0,
                    (i % 2 == 0) ? "BUY" : "SELL");

            webClient.post()
                    .uri("/trades/processTrades")
                    .bodyValue(trade)
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnNext(response -> System.out.println("Trade " + tradeId + " sent: " + response))
                    .subscribe();
        }

        return ResponseEntity.ok("100 trades sent successfully");
    }

}
