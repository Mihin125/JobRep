package com.demo.controller;

import com.demo.dto.RoleDto;
import com.demo.dto.UserSignUpDto;
import com.demo.dto.security.AdminSignUpDto;
import com.demo.dto.security.AdminUserDetailResponse;
import com.demo.model.User;
import com.demo.service.OfferService;
import com.demo.service.UserRoleService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/career/admin")
public class AdminController {

    @Autowired
    OfferService offerService;
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PutMapping("/accept-offer")
    public void acceptOffer(@RequestParam long offerId){
        offerService.acceptOffer(offerId);
    }

    @PutMapping("/reject-offer")
    public void rejectOffer(@RequestParam long offerId){
        offerService.rejectOffer(offerId);
    }

    @DeleteMapping ("/delete-offer")
    public void deleteOffer(@RequestParam long offerId) {
        offerService.deleteOfferById(offerId);
    }

    @PostMapping("/roles")
    public void saveRole(@RequestBody RoleDto roleDto){userRoleService.save(roleDto);
    }

    @GetMapping("/user/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/offer/all")
    public ResponseEntity<?> getAllOffers(@RequestParam String offerStatus) {

        return ResponseEntity.ok().body(offerService.getOffersByOfferStatus(offerStatus));

    }

    @DeleteMapping("/delete-user")
    public void deleteUser(@RequestParam long userId) {
        userService.deleteUser(userId);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody AdminSignUpDto adminSignUpDto) {
        userService.adminSignup(adminSignUpDto);
        return ResponseEntity.ok("SIGNUP_SUCCESS");
    }

}
