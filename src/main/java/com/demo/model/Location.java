package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    @JsonIgnore
    @OneToMany(mappedBy = "location")
    List<User> users;
    @JsonIgnore
    @OneToMany(mappedBy = "location")
    List<Offer> offers;


}
