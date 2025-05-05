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
    private ObjectMapper objectMapper; // Use the configured ObjectMapper bean

    private static final String JSON_FILE_PATH = "fund_data.json"; // or your actual path

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
        List<FundDTO> allFunds = getAllFunds(); // will use cache because of @Cacheable

        return allFunds.stream()
                .filter(fund -> fundType == null || fund.getFundType().equalsIgnoreCase(fundType))
                .filter(fund -> assetType == null || fund.getAssetType().equalsIgnoreCase(assetType))
                .filter(fund -> currency == null || fund.getCurrency().equalsIgnoreCase(currency))
                .filter(fund -> shareClass == null || fund.getShareClasses().stream()
                        .anyMatch(sc -> sc.getName().equalsIgnoreCase(shareClass)))
                .filter(fund -> {
                    Double price = fund.getPrice(); // Assuming FundDTO has a getPrice() method
                    return price != null && price > 0; // Example condition for price
                })
                .collect(Collectors.toList());
    }

}
