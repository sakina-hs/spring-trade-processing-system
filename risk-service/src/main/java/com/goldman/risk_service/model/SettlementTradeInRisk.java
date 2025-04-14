package com.goldman.risk_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettlementTradeInRisk {
    // private int tradeId;
    private String symbol;
    private int quantity;
    private double price;
    private String tradeType; // BUY or SELL

}
