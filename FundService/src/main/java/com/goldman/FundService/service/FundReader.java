package com.goldman.FundService.service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemReader;
import com.goldman.FundService.dto.FundDTO;
import com.goldman.FundService.model.Fund;
import com.goldman.FundService.repo.FundRepository;

public class FundReader implements ItemReader<FundDTO> {

    private final Iterator<FundDTO> fundIterator;

    public FundReader(FundRepository fundRepository) {
        // Fetch data from DB and map it to FundDTO
        List<FundDTO> fundList = fundRepository.findAllWithRelations().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        this.fundIterator = fundList.iterator(); // Iterator to go through the list one by one
    }

    @Override
    public FundDTO read() throws Exception {
        return fundIterator.hasNext() ? fundIterator.next() : null; // Return the next item or null if no more data
    }

    private FundDTO mapToDTO(Fund fund) {
        FundDTO dto = new FundDTO();
        dto.setFundName(fund.getName());
        dto.setFundType(fund.getFundType().getName());
        dto.setAssetType(fund.getAssetType().getName());
        dto.setCurrency(fund.getCurrency().getCode());
        dto.setShareClass(fund.getShareClasses().get(0).getName()); // Assuming the first share class is what you want
        return dto;
    }
}
