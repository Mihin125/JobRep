package com.demo.controller;

import com.demo.model.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("imania/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/sign-up")
    public HttpStatus signUp(@RequestBody User user){
        return userService.signUp(user);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable long id){
        return userService.findById(id);
    }
}
