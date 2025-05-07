package com.goldman.FundService.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldman.FundService.dto.FundDTO;
import com.goldman.FundService.model.Fund;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FundCachedService {

    @Autowired
    private ObjectMapper objectMapper;
    private static final String JSON_FILE_PATH = "/tmp/generated_files/fund_data.json";

    // TODO: Use a more robust caching mechanism in production
    @Cacheable("funds")

    public List<FundDTO> getAllFunds() {
        try {
            FundDTO[] fundsArray = objectMapper.readValue(new File(JSON_FILE_PATH), FundDTO[].class);
            return List.of(fundsArray);
        } catch (Exception e) {
            throw new RuntimeException("Error reading funds JSON file", e);
        }
    }

    public List<FundDTO> searchFunds(String fundType, String assetType, String currency, String shareClass) {
        List<FundDTO> allFunds = getAllFunds();

        return allFunds.stream()
                .filter(fund -> fundType == null || fund.getFundType().equalsIgnoreCase(fundType))
                .filter(fund -> assetType == null || fund.getAssetType().equalsIgnoreCase(assetType))
                .filter(fund -> currency == null || fund.getCurrency().equalsIgnoreCase(currency))
                .filter(fund -> shareClass == null || fund.getShareClasses().stream()
                        .anyMatch(sc -> sc.getName().equalsIgnoreCase(shareClass)))
                .filter(fund -> {
                    Double price = fund.getPrice();
                    return price != null && price > 0;
                })
                .collect(Collectors.toList());
    }

}
