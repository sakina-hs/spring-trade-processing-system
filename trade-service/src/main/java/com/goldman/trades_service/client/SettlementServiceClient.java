package com.goldman.trades_service.client;

import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import com.goldman.trades_service.model.SettlementTradeInRisk;

import org.springframework.web.bind.annotation.RequestBody;

@HttpExchange
public interface SettlementServiceClient {

    @PostExchange("/settlement")
    SettlementTradeInRisk saveTrade(@RequestBody SettlementTradeInRisk trade);

}