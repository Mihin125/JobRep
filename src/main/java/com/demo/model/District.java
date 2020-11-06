package com.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class District {
    @Id
    @GeneratedValue
    long districtId;
    String districtName;
//    @OneToMany(mappedBy = "district")
//    List<City> cities;
}
