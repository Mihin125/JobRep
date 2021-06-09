package com.demo.controller;

import com.demo.Authentication.ImaniaUserDetailsService;
import com.demo.Authentication.JwtRequestFilter;
import com.demo.Authentication.JwtUtil;
import com.demo.Authentication.UserRole;
import com.demo.Exceptions.CredentialsException;
import com.demo.dto.security.*;
import com.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/career/user")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private ImaniaUserDetailsService userDetailsService;

    @RequestMapping(value = "/log-in", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        } catch (AuthenticationException e){
            throw new CredentialsException(e.getMessage());
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final User user=jwtUtil.getUserByUsername(authenticationRequest.getUsername());

        if (!user.isStatus()) {
            return ResponseEntity.badRequest().body("Confirm the mail");
        }

        final String token = jwtUtil.generateToken(userDetails);
        final long personId = user.getId();
        List<UserRole> roles = user.getRoles();
        AuthDetailsResponse authDetailsResponse = new AuthDetailsResponse(user.getCompanyName(), token, authenticationRequest.getUsername(), LoginStatus.LOGIN_SUCCESS,personId, roles);
        if (roles.get(0).toString().equals("ROLE_ADMIN")) {
            return ResponseEntity.ok(new AuthenticationResponse(authDetailsResponse));
        }
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse(user.getCompanyName(), user.getContactNumber1(), user.getContactNumber2());
        return ResponseEntity.ok(new AuthenticationResponse(authDetailsResponse, userDetailsResponse));
    }

    private void authenticate(String username, String password) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @PostMapping(value = "/log-out")
    public void logout(){
        jwtRequestFilter.LogOutToken();
    }

}
