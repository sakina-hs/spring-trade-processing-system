package com.goldman.FundService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldman.FundService.model.AssetType;
import java.util.List;

@Repository
public interface AssetTypeRepository extends JpaRepository<AssetType, Integer> {

    List<AssetType> findByName(String name);
}