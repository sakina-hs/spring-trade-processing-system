package com.goldman.trade_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeEvent {
    private int tradeId;
    private String symbol;
    private int quantity;
    private double price;
}