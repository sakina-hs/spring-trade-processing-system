package com.goldman.trade_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class TradeRequest {
    private int tradeId;
    private String symbol;
    private int quantity;
    private double price;
}