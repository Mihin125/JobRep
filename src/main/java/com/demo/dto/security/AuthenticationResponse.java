package com.demo.dto.security;


import com.demo.Authentication.UserRole;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private AuthDetailsResponse authDetailsResponse;
    private UserDetailsResponse userDetailsResponse;

    public AuthenticationResponse(AuthDetailsResponse authDetailsResponse, UserDetailsResponse userDetailsResponse) {
        this.authDetailsResponse = authDetailsResponse;
        this.userDetailsResponse = userDetailsResponse;
    }

    public AuthenticationResponse(AuthDetailsResponse authDetailsResponse) {
        this.authDetailsResponse = authDetailsResponse;
    }
}
