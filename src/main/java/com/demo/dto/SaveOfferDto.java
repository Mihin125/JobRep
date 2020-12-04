package com.demo.dto;

import com.demo.model.ConditionCategory;
import com.demo.model.DeviceCategory;
import com.demo.model.User;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SaveOfferDto {

    private String modelName;
    private String description;
    private DeviceCategory category;
    private ConditionCategory conditionCategory;
    private double price;
    private long districtId;
    private long cityId;
    private int contactNumber1;
    private int contactNumber2;
    private String photo;
    private long user;

}
