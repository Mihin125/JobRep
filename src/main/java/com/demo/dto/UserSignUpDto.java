package com.demo.dto;

import com.demo.model.Location;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToOne;
@Getter
@Setter
public class UserSignUpDto {

    String username;
    String password;
    long districtId;
    long cityId;
    int contactNumber;
}
