package com.goldman.FundService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldman.FundService.dto.FundDTO;
import com.goldman.FundService.service.FundService;

import java.util.List;

@RestController
@RequestMapping("/api/funds")
public class FundController {

    @Autowired
    private FundService fundService;

    @GetMapping("/json")
    public List<FundDTO> getFundJson() {
        return fundService.getAllFundData();
    }
}
