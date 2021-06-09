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
public class User {//(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @Id
    @GeneratedValue
    long id;

    private String companyName;
    private String address;

    @JsonIgnore
    private String password;
    @ManyToOne
    private District district;

    private String contactNumber1;
    private String contactNumber2;

    @ManyToMany(fetch = FetchType.EAGER, cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(name = "user_roles",
    joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private List<UserRole> roles;

    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Offer> offers;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Offer> reportedOffers;

    String username;

    boolean status;

}
