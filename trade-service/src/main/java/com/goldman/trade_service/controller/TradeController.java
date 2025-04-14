package com.goldman.trade_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.goldman.trade_service.service.TradeDataGenerater;
import com.goldman.trade_service.service.TradeService;
import com.goldman.trade_service.model.TradeRequest;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @Autowired
    private TradeDataGenerater tradeDataGenerater;

    @GetMapping("/GenerateTrades")
    public ResponseEntity<String> GenerateTrade() {
        return tradeDataGenerater.sendMockTrades();
    }

    @PostMapping("/processTrades")
    public ResponseEntity<String> processTrades(@RequestBody TradeRequest request) {
        tradeService.processTrade(request);
        return ResponseEntity.ok("Trade placed successfully");
    }

}
