package com.goldman.risk_service.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldman.risk_service.model.SettlementTrade;
import com.goldman.trade_service.avro.TradeAvro;
import com.goldman.risk_service.client.SettlementServiceClient;
import java.util.List;

@Service
public class RiskService {

    @Autowired
    private SettlementServiceClient settlementServiceClient;

    public boolean checkRisk(TradeAvro tradeAvro) {
        // Get settlement trades from settlement service
        return true;
    }
}
