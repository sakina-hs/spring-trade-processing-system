package com.goldman.settlement_service.service;

import org.springframework.stereotype.Service;
import com.goldman.settlement_service.model.TradeEvent;

@Service
public class SettlementService {

    public void settleTrade(TradeEvent event) {
        // Add settlement logic here
        System.out.println("Settling trade: " + event.getTradeId());
    }
}
