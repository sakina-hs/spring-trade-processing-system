package com.goldman.FundService.service;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.goldman.FundService.dto.FundDTO; // Ensure FundDTO is imported

public class FundProcessor implements ItemProcessor<FundDTO, FundDTO> {

    @Override
    public FundDTO process(FundDTO item) throws Exception {
        return item; // No transformation needed in this example
    }
}