package com.goldman.settlement_service.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table(name = "settlement_trade")
public class SettlementTrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String symbol;
    private int quantity;
    private double price;
}