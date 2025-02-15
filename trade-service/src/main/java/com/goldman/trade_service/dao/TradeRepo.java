package com.goldman.trade_service.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.goldman.trade_service.model.Trade;

public interface TradeRepo extends JpaRepository<Trade, Integer> {

    List<Trade> findByTradeId(int tradeId);

}
