package com.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportOfferDto {
    private String description;
    private long OfferId;
    private long reportingUserId;
    private LocalDateTime dateTime;
}
