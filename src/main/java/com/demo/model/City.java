package com.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue
    long cityId;
    String city;
    @ManyToOne
    District district;
}
