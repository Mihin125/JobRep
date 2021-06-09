package com.demo.Authentication;

import com.demo.Exceptions.UserNotFoundException;
import com.demo.model.User;
import com.demo.repository.BlackListRepository;
import com.demo.repository.LogOutListRepository;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImaniaUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;
    @Autowired
    LogOutListRepository logOutListRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userService.findByEmail(email);

        if(user==null)
            throw new UserNotFoundException("email not found");
        return new UserPrincipal(user);
    }

    public void addLogOutToken(LogOutList logOutList){
        logOutListRepository.save(logOutList);
    }

    public Boolean isLoggedOut(String token){
        return logOutListRepository.findByBlackListedToken(token)==null;
    }

    public void deleteLoggedOutTokenByUsername(String username){
        logOutListRepository.deleteByUsername(username);
    }
}