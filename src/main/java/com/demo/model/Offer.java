package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Offer {
    @Id
    @GeneratedValue
    private long id;
    private String modelName;
    private String description;
    private DeviceCategory category;
    private ConditionCategory conditionCategory;
    private double price;
    @JsonIgnore
    @OneToOne
    private Location location;
    private int contactNumber1;
    private int contactNumber2;
    private String photo;
    private int viewCount;
    private LocalDateTime postedDate;
    private OfferStatus offerStatus;

}
