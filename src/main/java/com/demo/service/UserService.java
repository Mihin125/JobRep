package com.demo.service;

import com.demo.dto.UserSignUpDto;
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

    public HttpStatus signUp(UserSignUpDto userDto){
        if(userDto.getUsername()!=null &&
            userDto.getDistrictId()!=0 &&
            userDto.getCityId() != 0 &&
            userDto.getContactNumber()!=0 &&
            userDto.getPassword()!= null ) {

            User user=new User();
            user.setUsername(userDto.getUsername());
            user.setLocation(locationService.findLocationByDistrictAndCity(userDto.getDistrictId(),userDto.getCityId()));
            user.setPassword(userDto.getPassword());
            user.setContactNumber(userDto.getContactNumber());
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
