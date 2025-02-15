package com.goldman.settlement_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldman.settlement_service.model.SettlementTrade;
import com.goldman.settlement_service.service.SettlementService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/settlement")
public class SettlementController {

    @Autowired
    private SettlementService settlementService;

    @GetMapping("/settlement")
    public String getSettlement() {
        return "Hello World";
    }

    @PostMapping("/savetrades")
    public void saveTrade(@RequestBody SettlementTrade trade) {
        settlementService.saveTrade(trade);
    }
}
