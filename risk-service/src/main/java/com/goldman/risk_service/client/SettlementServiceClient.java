package com.goldman.risk_service.client;

import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.bind.annotation.RequestBody;
import com.goldman.risk_service.model.SettlementTradeInRisk;

@HttpExchange
public interface SettlementServiceClient {

    @PostExchange("/settlement")
    SettlementTradeInRisk saveTrade(@RequestBody SettlementTradeInRisk trade);

}