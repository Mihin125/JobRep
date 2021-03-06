package com.demo.Authentication;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class BlackList {
    @Id
    @GeneratedValue
    private int id;
    private String blackListedToken;
    private String username;
}
