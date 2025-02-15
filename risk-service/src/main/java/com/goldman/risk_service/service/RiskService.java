package com.goldman.risk_service.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldman.trade_service.avro.TradeAvro;
import com.goldman.risk_service.client.SettlementServiceClient;

@Service
public class RiskService {

    @Autowired
    private SettlementServiceClient settlementServiceClient;

    public boolean checkRisk(TradeAvro tradeAvro) {

        return true;
    }

}
