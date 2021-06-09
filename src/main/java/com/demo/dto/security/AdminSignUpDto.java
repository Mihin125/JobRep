package com.demo.dto.security;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminSignUpDto {

    private String name;
    private String password;
    private String email;
    private List<String> role;

}
