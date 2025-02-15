package com.goldman.risk_service.model;

import lombok.Data;

@Data
public class SettlementTrade {
    private int Id;
    private String symbol;
    private int quantity;
    private double price;
}
