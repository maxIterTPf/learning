package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface LoginService {

    User doCreate(String username, String password);

    List<User> findUserList(String username);
}
