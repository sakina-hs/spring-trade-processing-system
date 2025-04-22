package com.goldman.FundService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldman.FundService.model.AssetType;
import com.goldman.FundService.model.Currency;
import com.goldman.FundService.model.FundType;
import com.goldman.FundService.model.ShareClass;
import com.goldman.FundService.service.FundService;

import java.util.List;

@RestController
@RequestMapping("/fund-service")
public class FundServiceController {

    @Autowired
    private FundService fundService;

    @GetMapping("/asset-types")
    public List<AssetType> getAllAssetTypes() {
        return fundService.getAllAssetTypes();
    }

    @GetMapping("/currencies")
    public List<Currency> getAllCurrencies() {
        return fundService.getAllCurrencies();
    }

    @GetMapping("/fund-types")
    public List<FundType> getAllFundTypes() {
        return fundService.getAllFundTypes();
    }

    @GetMapping("/share-classes")
    public List<ShareClass> getAllShareClasses() {
        return fundService.getAllShareClasses();
    }
}