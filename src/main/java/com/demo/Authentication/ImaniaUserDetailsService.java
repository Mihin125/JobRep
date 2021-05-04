package com.demo.Authentication;

import com.demo.model.User;
import com.demo.repository.BlackListRepository;
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
    BlackListRepository blackListRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        if(user==null)throw new UsernameNotFoundException("Error 404: email Not found");
        return new UserPrincipal(user);
    }

    public void addBlackListToken(BlackList blackList){
        blackListRepository.save(blackList);
    }

    public Boolean isBlackListed(String token){
        return blackListRepository.findByBlackListedToken(token)==null;
    }

    public void deleteBlackListedByUsername(String username){
        blackListRepository.deleteByUsername(username);
    }
}
