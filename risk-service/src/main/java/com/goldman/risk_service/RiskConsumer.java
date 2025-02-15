package com.goldman.risk_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
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

    @Value("${spring.kafka.template.default-topic}")
    private String tradeTopic;

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void validateTrade(TradeAvro tradeAvro) {
        logger.info("Received trade for validation: {}", tradeAvro);
        boolean isValid = riskService.checkRisk(tradeAvro);

        if (isValid) {
            logger.info("Trade validated successfully: {}", tradeAvro.getTradeId());
            kafkaTemplate.send("trade-validated-topic", tradeAvro);
        } else {
            logger.warn("Trade failed risk checks: {}", tradeAvro.getTradeId());
        }

    }

}
