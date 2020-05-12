package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @RequestMapping("doLogin")
    public void doLogin() {
        log.info("url: {}, params: {}", "doLogin", "");
    }

}
