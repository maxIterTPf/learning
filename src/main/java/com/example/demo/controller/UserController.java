package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.Msg;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("addUserFieldLabel")
    public Msg<Void> addUserFieldLabel(@RequestBody JSONObject params) {
        return Msg.sucMsg(null);
    }

    @RequestMapping("addUserFieldConfig")
    public Msg<?> addUserFieldConfig(@RequestBody JSONObject params) {
        return userService.addUserFieldConfig(params);
    }

    @RequestMapping("editUserFieldConfig")
    public Msg<?> editUserFieldConfig(@RequestBody JSONObject params) {
        return userService.editUserFieldConfig(params);
    }

}
