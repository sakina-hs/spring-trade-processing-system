package com.goldman.FundService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goldman.FundService.dto.FundDTO;
import com.goldman.FundService.model.Fund;
import com.goldman.FundService.service.FundCachedService;
import com.goldman.FundService.service.FundService;

import java.util.List;

@RestController
@RequestMapping("/funds")
public class FundController {

    @Autowired
    private FundService fundService;

    @Autowired
    private FundCachedService fundCachedService;

    // @GetMapping("/json")
    // public List<FundDTO> getFundJson() {
    // return fundService.getAllFundData();
    // }

    @GetMapping
    public List<FundDTO> getAllFunds() {
        return fundCachedService.getAllFunds();
    }

    @GetMapping("/search")
    public List<FundDTO> searchFunds(
            @RequestParam(required = false) String fundType,
            @RequestParam(required = false) String assetType,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) String shareClass) {
        return fundCachedService.searchFunds(fundType, assetType, currency, shareClass);
    }
}
