package com.goldman.FundService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldman.FundService.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}