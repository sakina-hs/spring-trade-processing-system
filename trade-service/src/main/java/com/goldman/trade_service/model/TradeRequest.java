package com.goldman.trade_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeRequest {
    private int tradeId;
    private String fundname;
    private int quantity;
    private double price;
    private String tradeType;
    private String TradeUser; // BUY or SELL

}