package com.riekon.data.service;

import com.riekon.data.model.UserModel;
import com.riekon.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel findByName(String name) {
        return userRepository.findByName(name);
    }
}
