package com.demo.dto;

import com.demo.Authentication.UserRole;
import com.demo.model.Location;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserSignUpDto {

    String firstName;
    String LastName;
    String password;
    long districtId; //String
    long cityId; //String
    String contactNumber;
    String email;
    List<Long> role;
}
