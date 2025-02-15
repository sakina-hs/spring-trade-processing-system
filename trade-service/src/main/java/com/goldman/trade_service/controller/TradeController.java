package com.goldman.trade_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.goldman.trade_service.service.TradeService;
import com.goldman.trade_service.model.TradeRequest;
import com.goldman.trade_service.dao.TradeRepo;
import com.goldman.trade_service.model.Trade;

@RestController
@RequestMapping("/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @Autowired
    private TradeRepo tradeRepo;

    @PostMapping
    public ResponseEntity<String> placeTrade(@RequestBody TradeRequest request) {
        tradeService.processTrade(request);
        return ResponseEntity.ok("Trade placed successfully");
    }

    @PostMapping("/create")
    public ResponseEntity<Trade> createTrade(@RequestBody Trade trade) {
        Trade savedTrade = tradeService.createTrade(trade);
        return ResponseEntity.ok(savedTrade);
    }

    @GetMapping("/{tradeId}")
    public ResponseEntity<Trade> getTrade(@PathVariable int tradeId) {
        return tradeRepo.findById(tradeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
