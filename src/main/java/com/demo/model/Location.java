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
    long id;
    @ManyToOne
    District district;
    @ManyToOne
    City city;

}
