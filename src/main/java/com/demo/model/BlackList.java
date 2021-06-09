package com.demo.model;

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
    private long id;
    private String email;

    public BlackList() {
    }

    public BlackList(String email) {
        this.email = email;
    }
}
