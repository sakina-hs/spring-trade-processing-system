package com.goldman.risk_service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import com.goldman.risk_service.client.SettlementServiceClient;
import com.goldman.risk_service.model.SettlementTradeInRisk;
import com.goldman.risk_service.service.RiskService;
import com.goldman.trade_service.avro.TradeAvro;

import org.springframework.beans.factory.annotation.Value;

@Component
public class RiskConsumer {
    private static final Logger logger = LoggerFactory.getLogger(RiskConsumer.class);

    @Autowired
    private RiskService riskService;

    @Autowired
    private KafkaTemplate<String, TradeAvro> kafkaTemplate;

    @Autowired
    private SettlementServiceClient settlementServiceClient;

    @Value("${spring.kafka.template.default-topic}")
    private String tradeTopic;

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void validateTrade(List<TradeAvro> trades) {
        for (TradeAvro tradeAvro : trades) {
            logger.info("Received trade for validation: {}", tradeAvro);
            boolean isValid = riskService.checkRisk(tradeAvro);

            if (isValid) {
                logger.info("Trade validated successfully: {}", tradeAvro.getTradeId());
                try {
                    kafkaTemplate.send("trade-validated-topic", tradeAvro);
                } catch (Exception e) {
                    logger.info("error in kafka", e.getMessage());
                }
                settlementServiceClient.saveTrade(mapToSettlementTrade(tradeAvro));
            } else {
                logger.warn("Trade failed risk checks: {}", tradeAvro.getTradeId());
            }
        }

    }

    private SettlementTradeInRisk mapToSettlementTrade(TradeAvro tradeAvro) {

        SettlementTradeInRisk SettlementTradeInRisk = new SettlementTradeInRisk();
        // settlementTrade.setTradeId((int) tradeAvro.getTradeId()); // cast if Avro
        // type is long
        SettlementTradeInRisk.setSymbol(tradeAvro.getSymbol().toString()); // assuming 'security' = 'symbol'
        SettlementTradeInRisk.setQuantity(tradeAvro.getQuantity());
        SettlementTradeInRisk.setPrice(tradeAvro.getPrice());
        SettlementTradeInRisk.setTradeType(tradeAvro.getTradeType().toString()); // Avro enum to string

        logger.info("tradeavro in settlementTrade mapping:{}", SettlementTradeInRisk);
        return SettlementTradeInRisk;

    }
}
