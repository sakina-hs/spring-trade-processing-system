package com.goldman.risk_service.service;

import org.springframework.stereotype.Service;
import com.goldman.trade_service.avro.TradeAvro;

@Service
public class RiskService {

    public boolean checkRisk(TradeAvro tradeAvro) {
        return true;
    }

}
