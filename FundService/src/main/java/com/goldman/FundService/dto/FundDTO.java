package com.goldman.FundService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundDTO {

    private String fundName;
    private String fundType;
    private String assetType;
    private String currency;
    private String shareClass;

}
