package com.goldman.FundService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldman.FundService.model.ShareClass;

@Repository
public interface ShareClassRepository extends JpaRepository<ShareClass, Integer> {

    List<ShareClass> findByName(String name);
}