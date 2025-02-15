package com.goldman.settlement_service.client;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.bind.annotation.RequestParam;
import com.goldman.settlement_service.model.Trade;
import java.util.List;

public interface TradeServiceClient {
    @GetExchange("/trades")
    List<Trade> findAllTrades(@RequestParam String symbol);
}