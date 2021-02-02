package com.demo.dto;

import com.demo.model.ConditionCategory;
import com.demo.model.DeviceCategory;
import com.demo.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaveOfferDto {

    private String modelName;
    private String description;
    private DeviceCategory category;
    private ConditionCategory conditionCategory;
    private double price;
    private String district;
    private String city;
    private int contactNumber1;
    private int contactNumber2;
    private String photo;
    private long user;
    private LocalDateTime dateTime;

}
