package com.goldman.FundService.service;

import java.io.File;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldman.FundService.dto.FundDTO;

public class FundWriter implements ItemWriter<FundDTO> {

    private static final String FILE_PATH = "fund_data.json";

    @Override
    public void write(Chunk<? extends FundDTO> items) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(FILE_PATH);
        mapper.writeValue(file, items.getItems());
    }

}
