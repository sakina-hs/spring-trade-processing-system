package com.goldman.trades_service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import com.goldman.trade_service.avro.TradeAvro;
import com.goldman.trades_service.client.SettlementServiceClient;
import com.goldman.trades_service.model.SettlementTradeInRisk;
import com.goldman.trades_service.service.RiskService;

import org.springframework.beans.factory.annotation.Value;

@Component
public class TradeConsumer {
    private static final Logger logger = LoggerFactory.getLogger(TradeConsumer.class);

    @Autowired
    private RiskService riskService;

    @Autowired
    private KafkaTemplate<String, TradeAvro> kafkaTemplate;

    @Autowired
    private SettlementServiceClient settlementServiceClient;

    @Value("${spring.kafka.template.default-topic}")
    private String tradeTopic;

    public void processTrade(SettlementTradeInRisk request) {
        TradeAvro tradeAvro = TradeAvro.newBuilder()
                .setTradeId(request.getTradeId()) // Example field
                .setFundname(request.getFundname()) // Example field
                .setQuantity(request.getQuantity()) // Example field
                .setPrice(request.getPrice())
                .setTradeType(request.getTradeType())
                .setTradeUser("sakina")
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

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void validateTrade(List<TradeAvro> trades) {
        for (TradeAvro tradeAvro : trades) {
            if (tradeAvro == null) {
                logger.warn("Received null TradeAvro message, skipping...");
                continue; // Skip processing
            }

            logger.info("Received trade for validation: {}", tradeAvro);

            boolean isValid = riskService.checkRisk(tradeAvro);

            if (isValid) {
                logger.info("Trade validated successfully: {}", tradeAvro.getTradeId());
                try {
                    kafkaTemplate.send("trade-validated-topic", tradeAvro);
                } catch (Exception e) {
                    logger.error("Error in Kafka send: {}", e.getMessage(), e);
                }
                settlementServiceClient.saveTrade(mapToSettlementTrade(tradeAvro));
            } else {
                logger.warn("Trade failed risk checks: {}", tradeAvro.getTradeId());
            }
        }
    }

    private SettlementTradeInRisk mapToSettlementTrade(TradeAvro tradeAvro) {

        SettlementTradeInRisk SettlementTradeInRisk = new SettlementTradeInRisk();
        SettlementTradeInRisk.setTradeId((int) tradeAvro.getTradeId()); // cast if Avro
        SettlementTradeInRisk.setFundname(tradeAvro.getFundname().toString()); // assuming 'security' = 'symbol'
        SettlementTradeInRisk.setQuantity(tradeAvro.getQuantity());
        SettlementTradeInRisk.setPrice(tradeAvro.getPrice());
        SettlementTradeInRisk.setTradeType(tradeAvro.getTradeType().toString());
        SettlementTradeInRisk.setTradeUser(tradeAvro.getTradeUser().toString());

        logger.info("tradeavro in settlementTrade mapping:{}", SettlementTradeInRisk);
        return SettlementTradeInRisk;

    }
}
