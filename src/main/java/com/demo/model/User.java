package com.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    long userId;
    String username;
    String password;
    @OneToOne
    Location location;
    int contactNumber;
}
