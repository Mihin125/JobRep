package com.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserSignUpDto {

    private String firstName;
    private String LastName;
    private String password;
    private String district;
    private String city;
    private String contactNumber;
    private String email;
    private List<String> role;
}
