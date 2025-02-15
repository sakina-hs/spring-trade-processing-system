package com.goldman.settlement_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goldman.settlement_service.model.SettlementTrade;

public interface SettlementRepo extends JpaRepository<SettlementTrade, Integer> {
    List<SettlementTrade> findById(int id);

}
