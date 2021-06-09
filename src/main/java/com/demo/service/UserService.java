package com.demo.service;

import com.demo.Exceptions.*;
import com.demo.dto.UserSignUpDto;
import com.demo.dto.security.*;
import com.demo.model.BlackList;
import com.demo.model.Offer;
import com.demo.model.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DistrictService districtService;
    @Autowired
    BlackListService blackListService;
    @Autowired
    OfferService offerService;
    @Autowired
    UserRoleService userRoleService;

    @Transactional
    public void signUp(UserSignUpDto userDto) {

        User existingUser = findByEmail(userDto.getEmail());
        if (existingUser != null)
            throw new UserAlreadyExistsException("Email is found in the database");
        User user = new User();
        user.setCompanyName(userDto.getCompanyName());
        user.setDistrict(districtService.findDistrictByDistrictName(userDto.getDistrict()));
        user.setRoles(userDto.getRole().stream().map(x -> userRoleService.findByName(x)).collect(Collectors.toList()));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        String password = bCryptPasswordEncoder.encode(userDto.getPassword());
        user.setPassword(password);
        user.setContactNumber1(userDto.getContactNumber());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getEmail());
        user.setStatus(false);
        userRepository.save(user);

    }
    @Transactional(rollbackFor = UpdatingUserException.class)
    public User confirmEmail(User user){
        user.setStatus(true);
        return userRepository.save(user);

    }
    @Transactional(rollbackFor = UpdatingUserException.class)
    public HttpStatus updateUserDetails(long userId, UpdateUserDetailsDto updateUserDetailsDto) {

        User user;
        try {
            user = findById(userId);
        } catch (UserNotFoundException e) {
            throw new UpdatingUserException("Not Found");
        }

        if (updateUserDetailsDto.getCompanyName() != null)
            user.setCompanyName(updateUserDetailsDto.getCompanyName());
        if (updateUserDetailsDto.getContactNumber1() != null)
            user.setContactNumber1(updateUserDetailsDto.getContactNumber1());
        if (updateUserDetailsDto.getContactNumber2() != null)
            user.setContactNumber2(updateUserDetailsDto.getContactNumber2());
        return HttpStatus.OK;
    }

    @Transactional(rollbackFor = UserNotFoundException.class)
    public void updateUserPassword(long userId, UpdatePasswordDto updatePasswordDto) {

        User user = findById(userId);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        String password = bCryptPasswordEncoder.encode(updatePasswordDto.getNewPassword());
        user.setPassword(password);

    }
public  User getUserByEmail(String email){
    return userRepository.findByEmail(email);
}
    public User findById(long userId) {
        User user;
        try {
            user = userRepository.findById(userId).get();
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException("not found");
        }
        return user;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(rollbackFor = DeletingUserException.class)
    public void deleteUser(long userId) {

        User user;
        try {
            user = findById(userId);
        } catch (UserNotFoundException e) {
            throw new DeletingUserException("Not Found");
        }

        for (Offer offer : user.getOffers()) {
            offerService.deleteOfferById(offer.getId());
        }
        userRepository.delete(findById(userId));
    }

    public void banUser(long userId) {
        blackListService.save(new BlackList(findById(userId).getEmail()));
        deleteUser(userId);
    }

    public List<AdminUserDetailResponse> getAllUsers() {

        List<User> userList = userRepository.findAllUsers();

        List<AdminUserDetailResponse> adminUserDetailResponseList = new ArrayList<>();

        userList.forEach((user) -> {
            AdminUserDetailResponse adminUserDetailResponse = new AdminUserDetailResponse(user.getId(), user.getCompanyName(), user.getDistrict().getDistrictName(), user.getOffers().size());
            adminUserDetailResponseList.add(adminUserDetailResponse);
        });

        return adminUserDetailResponseList;

    }

    public void adminSignup(AdminSignUpDto adminSignUpDto) {

        User existingUser = findByEmail(adminSignUpDto.getEmail());
        if (existingUser != null)
            throw new UserAlreadyExistsException("Email is found in the database");
        User user = new User();
        user.setCompanyName(adminSignUpDto.getName());
        user.setRoles(adminSignUpDto.getRole().stream().map(x -> userRoleService.findByName(x)).collect(Collectors.toList()));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        String password = bCryptPasswordEncoder.encode(adminSignUpDto.getPassword());
        user.setPassword(password);
        user.setEmail(adminSignUpDto.getEmail());
        user.setUsername(adminSignUpDto.getEmail());
        userRepository.save(user);

    }

}
