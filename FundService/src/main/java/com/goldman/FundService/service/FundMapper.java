package com.goldman.FundService.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.goldman.FundService.config.DateTimeConfig;
import com.goldman.FundService.dto.FundDTO;
import com.goldman.FundService.dto.ShareclassDTO;
import com.goldman.FundService.model.Fund;

public class FundMapper {

    public static FundDTO mapToDTO(Fund fund) {
        FundDTO dto = new FundDTO();
        dto.setName(fund.getName());
        dto.setFundType(fund.getFundType().getName());
        dto.setAssetType(fund.getAssetType().getName());
        dto.setCurrency(fund.getCurrency().getCode());
        dto.setPrice(fund.getPrice());
        List<ShareclassDTO> shareClassDTOList = fund.getShareClasses().stream()
                .map(sc -> {
                    ShareclassDTO scDto = new ShareclassDTO();
                    scDto.setName(sc.getName());
                    scDto.setNavDate(sc.getNavDate());
                    return scDto;
                })
                .collect(Collectors.toList());
        dto.setShareClasses(shareClassDTOList);

        return dto;
    }
}
