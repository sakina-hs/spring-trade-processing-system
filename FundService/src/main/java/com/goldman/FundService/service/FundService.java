package com.goldman.FundService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.goldman.FundService.model.AssetType;
import com.goldman.FundService.model.Currency;
import com.goldman.FundService.model.FundType;
import com.goldman.FundService.model.ShareClass;
import com.goldman.FundService.repo.AssetTypeRepository;
import com.goldman.FundService.repo.CurrencyRepository;
import com.goldman.FundService.repo.FundTypeRepository;
import com.goldman.FundService.repo.ShareClassRepository;

import java.util.List;

@Service
public class FundService {

    @Autowired
    private AssetTypeRepository assetTypeRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private FundTypeRepository fundTypeRepository;
    @Autowired
    private ShareClassRepository shareClassRepository;

    public List<AssetType> getAllAssetTypes() {

        return assetTypeRepository.findAll();

    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();

    }

    public List<FundType> getAllFundTypes() {
        return fundTypeRepository.findAll();

    }

    public List<ShareClass> getAllShareClasses() {
        return shareClassRepository.findAll();
    }
}