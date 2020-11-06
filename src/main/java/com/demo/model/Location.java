package com.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue
    long locationId;
    @ManyToOne
    District district;
    String city;
//    @ManyToOne
//    City city;
//    @OneToOne(mappedBy = "location")
//    Offer offer;
}
