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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
    joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private List<UserRole> roles;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Offer> offers;
    @JsonIgnore
    @OneToMany
    private List<Offer> reportedOffers;
}
