package com.goldman.trades_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldman.trades_service.TradeConsumer;
import com.goldman.trades_service.model.SettlementTradeInRisk;

@RestController
@RequestMapping("/trades")
public class TradeController {

    @Autowired
    private TradeConsumer riskService;

    @PostMapping("/processTrades")
    public ResponseEntity<String> processTrades(@RequestBody SettlementTradeInRisk request) {
        riskService.processTrade(request);
        return ResponseEntity.ok("Trade placed successfully");
    }

}
