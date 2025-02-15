package com.goldman.risk_service.client;

import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.goldman.risk_service.model.SettlementTrade;

@HttpExchange
public interface SettlementServiceClient {
    @GetExchange("/settlement/trades")
    List<SettlementTrade> findAllSettlementTrades(@RequestParam int quantity);

    @PostExchange
    SettlementTrade saveTrade(@RequestBody SettlementTrade trade);
}