package com.goldman.FundService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldman.FundService.dto.FundDTO;
import com.goldman.FundService.model.Fund;
import com.goldman.FundService.model.ShareClass;
import com.goldman.FundService.repo.FundRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class FundService {

    @Autowired
    private FundRepository fundRepository;

    public List<FundDTO> getAllFundData() {
        List<Fund> funds = fundRepository.findAllWithRelations();
        List<FundDTO> dtoList = new ArrayList<>();

        for (Fund fund : funds) {
            for (ShareClass sc : fund.getShareClasses()) {
                FundDTO dto = new FundDTO();
                dto.setFundName(fund.getName());
                dto.setFundType(fund.getFundType().getName());
                dto.setAssetType(fund.getAssetType().getName());
                dto.setCurrency(fund.getCurrency().getCode());
                dto.setShareClass(sc.getName());
                dtoList.add(dto);
            }
        }

        return dtoList;
    }
}
