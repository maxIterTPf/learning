package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.repository.UserFieldLabelRepository;
import com.example.demo.util.HttpsUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserFieldLabelRepository userFieldLabelRepository;

    private String userName;

    @Test
    void contextLoads() {
    }

}
