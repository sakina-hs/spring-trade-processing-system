package com.goldman.FundService.repo;

import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.goldman.FundService.model.Fund;

@Repository
public interface FundRepository extends JpaRepository<Fund, Long> {

    @Query("SELECT f FROM Fund f JOIN FETCH f.fundType ft JOIN FETCH f.assetType at JOIN FETCH f.currency c JOIN FETCH f.shareClasses sc")
    List<Fund> findAllWithRelations();
}
