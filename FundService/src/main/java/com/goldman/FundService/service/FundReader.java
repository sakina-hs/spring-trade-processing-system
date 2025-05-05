package com.goldman.FundService.service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemReader;
import com.goldman.FundService.dto.FundDTO;
import com.goldman.FundService.repo.FundRepository;

public class FundReader implements ItemReader<FundDTO> {

    private final Iterator<FundDTO> fundIterator;

    public FundReader(FundRepository fundRepository) {
        // Fetch data from DB and map it to FundDTO
        List<FundDTO> fundList = fundRepository.findAllWithRelations().stream()
                .map(FundMapper::mapToDTO)
                .collect(Collectors.toList());
        this.fundIterator = fundList.iterator();
    }

    @Override
    public FundDTO read() throws Exception {
        return fundIterator.hasNext() ? fundIterator.next() : null;
    }
}
