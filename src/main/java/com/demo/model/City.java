package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue
    long id;
    String city;
    @ManyToOne
    District district;
    @JsonIgnore
    @OneToMany(mappedBy = "city")
    List<User> users;
    @JsonIgnore
    @OneToMany(mappedBy = "city")
    List<Offer> offers;
}
