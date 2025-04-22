package com.goldman.FundService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldman.FundService.model.FundType;

@Repository
public interface FundTypeRepository extends JpaRepository<FundType, Integer> {

    List<FundType> findByName(String name);
}