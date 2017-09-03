package com.riekon.aws.service;

import com.riekon.aws.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceHandler implements UserDetailsService {
    public static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceHandler.class);

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserModel userModel = userService.findByName(userName);
        if (userModel == null) {
            logger.error("Failed authorization attempt for user {}", userName);
            logger.error("  reason: user not known to the health");
            return null;
        }

        List<GrantedAuthority> authList = new ArrayList<>();
        for (String role : userModel.getRoles()) authList.add(new SimpleGrantedAuthority(role));
        return new User(userName, userModel.getPassword(), authList);
    }
}