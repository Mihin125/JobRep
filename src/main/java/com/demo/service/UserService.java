package com.demo.service;

import com.demo.dto.UserSignUpDto;
import com.demo.model.BlackList;
import com.demo.model.Offer;
import com.demo.model.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LocationService locationService;
    @Autowired
    BlackListService blackListService;
    @Autowired
    OfferService offerService;

    public HttpStatus signUp(UserSignUpDto userDto){
        User exisitingUser = findByEmail(userDto.getEmail());
        if(blackListService.findByEmail(userDto.getEmail())!=null)return HttpStatus.FORBIDDEN;
        if (exisitingUser!=null)return HttpStatus.BAD_REQUEST;
        if(userDto.getUsername()!=null &&
            userDto.getDistrictId()!=0 &&
            userDto.getCityId() != 0 &&
            userDto.getContactNumber()!=0 &&
            userDto.getPassword()!= null ) {

            User user=new User();
            user.setUsername(userDto.getUsername());
            user.setLocation(locationService.findLocationByDistrictAndCity(userDto.getDistrictId(),userDto.getCityId()));
            user.setRole(userDto.getRole());
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(12);
            String password =bCryptPasswordEncoder.encode(userDto.getPassword());
            user.setPassword(password);
            user.setContactNumber(userDto.getContactNumber());
            user.setEmail(userDto.getEmail());
            if (userRepository.save(user) != null) {
                return HttpStatus.OK;
            }
        }return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus updateUser(long userId,UserSignUpDto updateUserDto){
        try{
            findById(userId);
        }catch (NullPointerException ex){
            return HttpStatus.NOT_FOUND;
        }
        User user = findById(userId);
        if(updateUserDto.getUsername()!=null)user.setUsername(updateUserDto.getUsername());
        if(updateUserDto.getPassword()!=null)user.setPassword(updateUserDto.getPassword());
        if(updateUserDto.getContactNumber()!=0)user.setContactNumber(updateUserDto.getContactNumber());
        if(updateUserDto.getDistrictId()!=0.0)user.setLocation(locationService.findLocationByDistrictAndCity(updateUserDto.getDistrictId(),updateUserDto.getCityId()));
        if(updateUserDto.getEmail()!=null)user.setEmail(updateUserDto.getEmail());
        userRepository.save(user);
        return HttpStatus.OK;
    }

    public User findById(long userId){
        return userRepository.findById(userId).orElseThrow(NullPointerException::new);
    }

    public User findByusername(String username){
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email){return  userRepository.findByEmail(email);}

    public void deleteUser(long userId){
        User user = findById(userId);
        userRepository.deleteFromUserReportedOffers(userId);
        for (Offer offer: user.getOffers()) {
            offerService.deleteOffer(offer.getId());
        }
        userRepository.delete(findById(userId));
    }

    public void banUser(long userId){
        blackListService.save(new BlackList(findById(userId).getEmail()));
        deleteUser(userId);
    }
}
