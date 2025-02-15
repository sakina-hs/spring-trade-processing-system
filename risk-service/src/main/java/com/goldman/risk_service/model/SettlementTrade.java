package com.goldman.risk_service.model;

import lombok.Data;

@Data
public class SettlementTrade {
    private int id;
    private String name;
    private int quantity;
}