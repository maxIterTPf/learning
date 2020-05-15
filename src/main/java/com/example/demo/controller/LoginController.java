package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.Msg;
import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("doLogin")
    public Msg<User> doLogin(@RequestBody JSONObject params) {
        Msg<User> msg = new Msg<>();
        if (params != null) {
            String username = params.getString("username");
            if (StringUtils.isEmpty(username)) {
                msg.setErr("用户名为空");
            } else {
                List<User> userList = loginService.findUserList(username);
                if (CollectionUtils.isEmpty(userList)) {
                    msg.setErr("账号不存在");
                } else if (userList.size() == 1) {
                    String password = params.getString("password");
                    if (StringUtils.isEmpty(password)) {
                        msg.setErr("密码为空");
                    } else {
                        if (userList.get(0).verify(password)) {
                            msg.setCode(Msg.SUCCESS);
                            msg.setData(userList.get(0));
                        } else {
                            msg.setErr("密码错误");
                        }
                    }
                } else {
                    msg.setErr("账号异常，无法登录，请联系管理员");
                }
            }
        }
        return msg;
    }

    @RequestMapping("doCreate")
    public Msg<User> doCreate(@RequestBody JSONObject params) {
        Msg<User> msg = new Msg<>();
        if (params != null) {
            String username = params.getString("username");
            if (StringUtils.isEmpty(username)) {
                msg.setErr("用户名为空");
            } else {
                List<User> userList = loginService.findUserList(username);
                if (CollectionUtils.isEmpty(userList)) {
                    String password = params.getString("password");
                    if (StringUtils.isEmpty(password)) {
                        msg.setErr("密码为空");
                    } else {
                        msg.setCode(Msg.SUCCESS);
                        msg.setData(loginService.doCreate(username, password));
                    }
                } else {
                    msg.setErr("账号已存在");
                }
            }
        }
        return msg;
    }

}
