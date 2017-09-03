package com.riekon.aws.service;

import com.riekon.aws.model.UserModel;
import com.riekon.aws.repository.UserRepository;
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
