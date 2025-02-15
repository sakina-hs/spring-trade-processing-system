package com.goldman.settlement_service.model;

import lombok.Data;

@Data
public class Trade {
    private int tradeId;
    private String symbol;
    private int quantity;
    private double price;
}