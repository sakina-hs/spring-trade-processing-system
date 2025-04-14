package com.goldman.settlement_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "settlement_trade")
public class SettlementTrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tradeId;
    private String symbol;
    private int quantity;
    private double price;
    private String tradeType; // BUY or SELL

}