package com.goldman.settlement_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldman.settlement_service.model.SettlementDto;
import com.goldman.settlement_service.model.SettlementTrade;
import com.goldman.settlement_service.service.SettlementService;

import org.springframework.web.bind.annotation.RequestBody; // Correct import for @RequestBody

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/settlement")
public class SettlementController {

    private static final Logger logger = LoggerFactory.getLogger(SettlementController.class);

    @Autowired
    private SettlementService settlementService;

    @PostMapping
    public void saveTrade(@RequestBody SettlementDto trade) {
        logger.info("Received in controller" + trade);
        settlementService.saveTrade(trade);
    }
}
