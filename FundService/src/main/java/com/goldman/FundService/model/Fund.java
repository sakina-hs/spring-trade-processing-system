package com.goldman.FundService.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
public class Fund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("fundName")
    private String name;
    private String fundCode;
    private LocalDate inceptionDate;
    private double price;

    @ManyToOne
    private FundType fundType;

    @ManyToOne
    private AssetType assetType;

    @ManyToOne
    private Currency currency;

    @OneToMany(mappedBy = "fund", cascade = CascadeType.ALL)
    @JsonProperty("shareClass")
    private List<ShareClass> shareClasses;
}
