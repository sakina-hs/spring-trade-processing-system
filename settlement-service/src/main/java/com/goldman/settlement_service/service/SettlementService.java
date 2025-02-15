package com.goldman.settlement_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldman.settlement_service.model.SettlementTrade;
import com.goldman.settlement_service.repo.SettlementRepo;

@Service
public class SettlementService {

    @Autowired
    SettlementRepo repository;

    public void saveTrade(SettlementTrade trade) {
        repository.save(trade);
    }
}
