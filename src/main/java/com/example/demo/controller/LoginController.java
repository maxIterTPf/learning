package com.example.demo.controller;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

//   private final Log log = LogFactory.getLog(getClass());

    @RequestMapping("doLogin")
    public void doLogin() {
    }

}
