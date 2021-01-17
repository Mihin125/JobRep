package com.demo.service;

import com.demo.Authentication.UserRole;
import com.demo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    public UserRole findById(long id){
        return userRoleRepository.findById(id).orElseThrow(NullPointerException::new);
    }
}
