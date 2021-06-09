package com.demo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OfferResponseDto {

    private String companyName;
    private String workingNature;
    private String district;
    private String category;
    private String positions;
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
    private long id;

    public OfferResponseDto(String companyName ,String workingNature, String district, String category, String positions, LocalDateTime openingDate, LocalDateTime closingDate, long id) {
        this.companyName = companyName;
        this.workingNature = workingNature;
        this.district = district;
        this.category = category;
        this.positions = positions;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.id = id;
    }
}
