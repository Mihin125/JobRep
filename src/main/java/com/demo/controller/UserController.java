package com.demo.controller;

import com.demo.Authentication.UserRole;
import com.demo.Exceptions.CredentialsException;
import com.demo.dto.UserSignUpDto;
import com.demo.dto.security.*;
import com.demo.model.User;
import com.demo.service.EmailService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/career/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserSignUpDto user) {
        userService.signUp(user);
        emailService.sendEmail(user.getEmail());
        return ResponseEntity.ok("Please check your mail inbox");
    }
    @GetMapping("/email-confirm")
    public String confirmEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        userService.confirmEmail(user);
        return "Email Confirmed !";
    }

    @PutMapping("/update/{userId}")
    public HttpStatus updateUserDetails(@PathVariable long userId, @RequestBody UpdateUserDetailsDto updateUserDetailsDto) {
        return userService.updateUserDetails(userId, updateUserDetailsDto);
    }

    @PutMapping("/update/password/{userId}")
    public ResponseEntity<?> updateUserPassword(@PathVariable long userId, @RequestBody UpdatePasswordDto updatePasswordDto) {
        try {
            authenticate(updatePasswordDto.getUsername(), updatePasswordDto.getCurrentPassword());

        } catch (AuthenticationException e) {
            throw new CredentialsException("Invalid credentials");
        }
        userService.updateUserPassword(userId, updatePasswordDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public UserDetailsResponse findById(@PathVariable long userId) {
        User user = userService.findById(userId);
        return new UserDetailsResponse(user.getCompanyName(), user.getContactNumber1(), user.getContactNumber2());
    }

    @DeleteMapping("/admin/ban-user/{userId}")
    public void banUser(@PathVariable long userId) {
        userService.banUser(userId);
    }

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }


    private void authenticate(String username, String password) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
