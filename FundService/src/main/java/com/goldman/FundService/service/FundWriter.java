package com.goldman.FundService.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldman.FundService.dto.FundDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component
public class FundWriter implements ItemWriter<FundDTO> {

    private static final String FILE_PATH = "/tmp/generated_files/fund_data.json";

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void write(@NonNull Chunk<? extends FundDTO> items) throws Exception {
        File file = new File(FILE_PATH);

        File directory = new File(file.getParent());
        if (!directory.exists()) {
            boolean dirsCreated = directory.mkdirs();
            if (!dirsCreated) {
                throw new IOException("Failed to create directory: " + directory.getAbsolutePath());
            }
        }

        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                throw new IOException("Failed to create file: " + file.getAbsolutePath());
            }
        }

        try {
            List<? extends FundDTO> fundList = items.getItems();
            objectMapper.writeValue(file, fundList);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error writing FundDTO data to JSON file", e);
        }
    }
}
