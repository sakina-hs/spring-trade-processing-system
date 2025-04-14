package com.goldman.settlement_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementDto {
    // private int tradeId;
    private String symbol;
    private int quantity;
    private double price;
    private String tradeType;

}
