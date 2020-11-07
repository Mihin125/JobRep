package com.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Offer {
    @Id
    @GeneratedValue
    private long offerId;
    private String modelName;
    private String description;
    @Enumerated
    private DeviceCategory category;
    private ConditionCategory conditionCategory;
    private double price;
    @OneToOne
    private Location location;
    private int contactNumber1;
    private int contactNumber2;
    private String photo;
    private int viewCount;

}
