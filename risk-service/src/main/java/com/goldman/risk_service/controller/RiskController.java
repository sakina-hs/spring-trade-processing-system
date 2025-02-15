package com.goldman.risk_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goldman.risk_service.client.SettlementServiceClient;
import com.goldman.risk_service.model.SettlementTrade;

@RestController
@RequestMapping("/risk")
public class RiskController {

    @Autowired
    private SettlementServiceClient settlementServiceClient;

    @GetMapping("/settlement-trades")
    public List<SettlementTrade> getSettlementTrades(@RequestParam int quantity) {
        return settlementServiceClient.findAllSettlementTrades(quantity);
    }

}
