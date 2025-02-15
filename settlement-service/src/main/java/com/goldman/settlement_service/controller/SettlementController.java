package com.goldman.settlement_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/settlement")
public class SettlementController {

    @GetMapping("/settlement")
    public String getSettlement() {
        return "Hello World";
    }
}
