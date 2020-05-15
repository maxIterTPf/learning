package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.Msg;
import com.example.demo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("doLogin")
    public Msg<?> doLogin(@RequestBody JSONObject params) {
        return loginService.doLogin(params);
    }

    @RequestMapping("doCreate")
    public Msg<?> doCreate(@RequestBody JSONObject params) {
        return loginService.doCreate(params);
    }

}
