package com.goldman.FundService.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.goldman.FundService.model.ShareClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FundDTO {

    @JsonProperty("fundName")
    private String name;
    private String fundType;
    private String assetType;
    private String currency;
    private Double price;
    @JsonProperty("shareClass")
    private List<ShareclassDTO> shareClasses;

}
