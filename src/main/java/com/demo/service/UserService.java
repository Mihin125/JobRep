package com.demo.service;

import com.demo.model.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LocationService locationService;

    public HttpStatus signUp(User user){
        if(user.getUsername()!=null &&
            user.getLocation()!=null &&
            user.getContactNumber()!=0 &&
            user.getPassword()!= null ) {
            locationService.saveLocation(user.getLocation());
            if (userRepository.save(user) != null) {
                return HttpStatus.OK;
            }
        }return HttpStatus.BAD_REQUEST;
    }

    public User findById(long userId){
        return userRepository.findById(userId).orElseThrow(NullPointerException::new);
    }

    public User findByusername(String username){
        return userRepository.findByUsername(username);
    }
}
