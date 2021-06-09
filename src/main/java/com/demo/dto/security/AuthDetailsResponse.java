package com.demo.dto.security;

import com.demo.Authentication.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AuthDetailsResponse {

    private String companyName;
    private  String jwtToken;
    private  String username;
    private  LoginStatus loginStatus;
    private long personId;
    private List<UserRole> roles;

    public AuthDetailsResponse(String username, LoginStatus loginStatus) {
        this.username = username;
        this.loginStatus = loginStatus;
    }
}
