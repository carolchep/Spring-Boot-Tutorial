package com.example.caro.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Override
    //validation for user existence
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if(userName.equals("carol")){
          //  return new User(username:"carol",password:"secret", new ArrayList<>())
            return new User("carol","secret",new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("user does not exist");
        }

    }
}
