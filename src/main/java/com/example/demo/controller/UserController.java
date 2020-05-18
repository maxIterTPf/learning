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

    @RequestMapping("addUserFieldConfig")
    public Msg<?> addUserFieldConfig(@RequestBody JSONObject params) {
        return userService.addUserFieldConfig(params);
    }

    @RequestMapping("deleteUserFieldConfig")
    public Msg<?> deleteUserFieldConfig(@RequestBody JSONObject params) {
        return userService.deleteUserFieldConfig(params);
    }

    @RequestMapping("editUserFieldConfig")
    public Msg<?> editUserFieldConfig(@RequestBody JSONObject params) {
        return userService.editUserFieldConfig(params);
    }

    @RequestMapping("findUserFieldConfig")
    public Msg<?> findUserFieldConfig(@RequestBody JSONObject params) {
        return userService.findUserFieldConfig(params);
    }

    @RequestMapping("editUserFieldLabel")
    public Msg<?> editUserFieldLabel(@RequestBody JSONObject params) {
        return userService.editUserFieldLabel(params);
    }

    @RequestMapping("findUserFieldLabel")
    public Msg<?> findUserFieldLabel(@RequestBody JSONObject params) {
        return userService.findUserFieldLabel(params);
    }

}
