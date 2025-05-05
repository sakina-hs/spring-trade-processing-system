package com.goldman.FundService.service;

import java.io.File;
import java.io.IOException;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldman.FundService.dto.FundDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component
public class FundWriter implements ItemWriter<FundDTO> {

    private static final String FILE_PATH = "fund_data.json";

    @Autowired
    private ObjectMapper objectMapper; // Use the configured ObjectMapper bean

    @Override
    public void write(@NonNull Chunk<? extends FundDTO> items) throws Exception {
        File file = new File(FILE_PATH);

        // Ensure the file exists
        if (!file.exists()) {
            file.createNewFile();
        }

        // Handle writing to the file
        try {
            objectMapper.writeValue(file, items.getItems());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error writing to file", e);
        }
    }
}
