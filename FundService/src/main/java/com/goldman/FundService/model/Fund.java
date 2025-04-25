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

@Data
@Entity
public class Fund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String fundCode;
    private LocalDate inceptionDate;

    @ManyToOne
    private FundType fundType;

    @ManyToOne
    private AssetType assetType;

    @ManyToOne
    private Currency currency;

    @OneToMany(mappedBy = "fund", cascade = CascadeType.ALL)
    private List<ShareClass> shareClasses;
}
