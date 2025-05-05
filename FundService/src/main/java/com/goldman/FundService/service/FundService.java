package com.goldman.FundService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldman.FundService.dto.FundDTO;
import com.goldman.FundService.model.Fund;
import com.goldman.FundService.repo.FundRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FundService {

    @Autowired
    private FundRepository fundRepository;

    public List<FundDTO> getAllFundData() {
        List<Fund> funds = fundRepository.findAllWithRelations();
        return funds.stream()
                .map(FundMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
