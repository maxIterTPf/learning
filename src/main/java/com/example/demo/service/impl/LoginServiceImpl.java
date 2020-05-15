package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User doCreate(String username, String password) {
        return userRepository.save(User.create(username, password));
    }

    @Override
    public List<User> findUserList(String username) {
        return userRepository.findByUsername(username);
    }


}
