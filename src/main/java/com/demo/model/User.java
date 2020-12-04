package com.demo.model;

import com.demo.Authentication.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    long id;
    private String username;
    private String password;
    @JsonIgnore
    @ManyToOne
    private Location location;
    private int contactNumber;
    private UserRole role;
    private String email;
    //@JsonIgnore
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Offer> offers;
    @JsonIgnore
    @OneToMany
    private List<Offer> reportedOffers;
}
