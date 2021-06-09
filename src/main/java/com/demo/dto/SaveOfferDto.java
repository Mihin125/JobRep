package com.demo.dto;

import com.demo.model.Category;
import com.demo.model.WorkingNature;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaveOfferDto {

    private String positions;
    private String workingNature;
    private String category;
    private double price;
    private String district;
    private String photo;
    private long user;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime postedDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredDate;

}
