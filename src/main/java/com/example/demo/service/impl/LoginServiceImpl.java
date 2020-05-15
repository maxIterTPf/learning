package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.Msg;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Msg<?> doLogin(JSONObject params) {
        if (params == null) {
            return Msg.errMsg("参数为空");
        }
        String username = params.getString("username");
        if (StringUtils.isEmpty(username)) {
            return Msg.errMsg("用户名为空");
        }
        long count = userRepository.countByUsername(username);
        if (count < 1) {
            return Msg.errMsg("账号不存在");
        }
        if (count > 1) {
            return Msg.errMsg("账号异常，无法登录，请联系管理员");
        }
        String password = params.getString("password");
        if (StringUtils.isEmpty(password)) {
            return Msg.errMsg("密码为空");
        }
        User user = userRepository.findOneByUsername(username).orElse(null);
        if (user == null) {
            return Msg.errMsg("账号不存在");
        }
        if (user.verify(password)) {
            return Msg.sucMsg(user);
        }
        return Msg.errMsg("密码错误");
    }

    @Override
    public Msg<?> doCreate(JSONObject params) {
        if (params == null) {
            return Msg.errMsg("参数为空");
        }
        String username = params.getString("username");
        if (StringUtils.isEmpty(username)) {
            return Msg.errMsg("用户名为空");
        }
        long count = userRepository.countByUsername(username);
        if (count >= 1) {
            return Msg.errMsg("账号已存在");
        }
        String password = params.getString("password");
        if (StringUtils.isEmpty(password)) {
            return Msg.errMsg("密码为空");
        }
        return Msg.sucMsg(userRepository.save(User.create(username, password)));
    }

}
