package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    @ManyToOne
    private User user;
    //@JsonIgnore
    @ManyToOne
    private City city;
    private int contactNumber1;
    private int contactNumber2;
    private String photo;
    private int viewCount;
    private LocalDateTime postedDate;
    private OfferStatus offerStatus;
    @ManyToOne
    private District district;
    @JsonIgnore
    @OneToMany(mappedBy = "offer")
    private List<ReportOffer> reports;

    public Offer(long id, String modelName, String description, DeviceCategory category, ConditionCategory conditionCategory, double price, User user,District district, int contactNumber1, int contactNumber2, String photo, int viewCount, LocalDateTime postedDate, OfferStatus offerStatus, City city) {
        this.id = id;
        this.modelName = modelName;
        this.description = description;
        this.category = category;
        this.conditionCategory = conditionCategory;
        this.price = price;
        this.user = user;
        List<Offer> offerList = user.getOffers();
        offerList.add(this);
        user.setOffers(offerList);
        this.city = city;
        this.district = district;
        this.contactNumber1 = contactNumber1;
        this.contactNumber2 = contactNumber2;
        this.photo = photo;
        this.viewCount = viewCount;
        this.postedDate = postedDate;
        this.offerStatus = offerStatus;
        this.district = district;
    }

    public Offer() {
    }
}
