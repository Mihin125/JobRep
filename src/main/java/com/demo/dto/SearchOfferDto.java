package com.demo.dto;

import com.demo.model.ConditionCategory;
import com.demo.model.DeviceCategory;
import com.demo.model.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchOfferDto {
    private String keyword;
    private DeviceCategory category;
    private ConditionCategory conditionCategory;
    private double priceRangeUpper;
    private double priceRangeLower;
    private Location locationDistrict;
}
