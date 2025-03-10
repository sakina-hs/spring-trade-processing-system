package com.goldman.trade_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import com.goldman.trade_service.model.TradeRequest;
import org.springframework.beans.factory.annotation.Value;
import com.goldman.trade_service.avro.TradeAvro;

@Service
public class TradeService {
    private static final Logger logger = LoggerFactory.getLogger(TradeService.class);
    @Autowired
    private KafkaTemplate<String, TradeAvro> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String tradeTopic;

    public void processTrade(TradeRequest request) {
        TradeAvro tradeAvro = TradeAvro.newBuilder()
                .setTradeId(request.getTradeId()) // Example field
                .setSymbol(request.getSymbol())
                .setQuantity(request.getQuantity()) // Example field
                .setPrice(request.getPrice()) // Example field
                .build();
        logger.info("Producing Kafka message: {}", tradeAvro);
        kafkaTemplate.send(tradeTopic, tradeAvro)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        logger.info("Message sent successfully to topic: {}", tradeTopic);
                    } else {
                        logger.error("Failed to send message", ex);
                    }
                });
    }

    public void cancelTrade(TradeRequest request) {
        TradeAvro tradeAvro = TradeAvro.newBuilder()
                .setTradeId(request.getTradeId()) // Example field
                .setSymbol(request.getSymbol())
                .setQuantity(request.getQuantity()) // Example field
                .setQuantity(request.getQuantity()) // Example field
                .build();
        kafkaTemplate.send("trade-cancelled-topic", tradeAvro);
        System.out.println("Trade cancelled: " + tradeAvro.getTradeId());
    }

}