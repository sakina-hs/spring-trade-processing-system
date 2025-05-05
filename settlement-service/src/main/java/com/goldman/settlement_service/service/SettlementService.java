package com.goldman.settlement_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldman.settlement_service.model.SettlementDto;
import com.goldman.settlement_service.model.SettlementTrade;
import com.goldman.settlement_service.repo.SettlementRepo;

@Service
public class SettlementService {

    @Autowired
    SettlementRepo repository;

    private final Logger logger = LoggerFactory.getLogger(SettlementService.class);

    public void saveTrade(SettlementDto trade) {

        logger.info("came into saveTrade method" + trade);
        repository.save(toEntity(trade));
    }

    public static SettlementTrade toEntity(SettlementDto dto) {
        SettlementTrade trade = new SettlementTrade();
        trade.setTradeId(dto.getTradeId());
        trade.setFundname(dto.getFundname());
        trade.setQuantity(dto.getQuantity());
        trade.setPrice(dto.getPrice());
        trade.setTradeType(dto.getTradeType());
        return trade;
    }
}
