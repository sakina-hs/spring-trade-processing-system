package com.goldman.settlement_service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import com.goldman.settlement_service.service.SettlementService;
import com.goldman.settlement_service.model.TradeEvent;

@Component
public class SettlementConsumer {

    @Autowired
    private SettlementService settlementService;

    // @Autowired
    // private KafkaTemplate<String, TradeEvent> kafkaTemplate;

    @KafkaListener(topics = "trade-validated-topic")
    public void processSettlement(TradeEvent event) {
        settlementService.settleTrade(event);
        // kafkaTemplate.send("trade-settled-topic", event);
        System.out.println("Trade settled: " + event.getTradeId());
    }

}
