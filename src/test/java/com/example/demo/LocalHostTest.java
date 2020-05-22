package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.HttpsUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Slf4j
public class LocalHostTest {

    private static final String BASE_URL = "https://localhost:8080";
    private static final String DO_LOGIN = BASE_URL + "/doLogin";
    private static final String DO_CREATE = BASE_URL + "/doCreate";
    private static final String ADD_USER_FIELD_CONFIG = BASE_URL + "/user/addUserFieldConfig";
    private static final String DELETE_USER_FIELD_CONFIG = BASE_URL + "/user/deleteUserFieldConfig";
    private static final String EDIT_USER_FIELD_CONFIG = BASE_URL + "/user/editUserFieldConfig";
    private static final String FIND_USER_FIELD_CONFIG = BASE_URL + "/user/findUserFieldConfig";

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
        System.out.println(DigestUtils.md5DigestAsHex("MessageDigest MD5".getBytes()));
    }

    @Test
    public void doLogin() {
        JSONObject json = new JSONObject();
        json.put("username", "111");
        json.put("password", "123123");
        String post = HttpsUtil.doPost(DO_LOGIN, json.toJSONString());
        log.info("\nurl: {} \njson: {} \npost: {}", DO_LOGIN, json, post);
    }

    @Test
    public void doCreate() {
        JSONObject json = new JSONObject();
        json.put("username", "huqinggang");
        json.put("password", "123456");
        String post = HttpsUtil.doPost(DO_CREATE, json.toJSONString());
        log.info("\nurl: {} \njson: {} \npost: {}", DO_CREATE, json, post);
    }

    @Test
    public void addUserFieldConfig() {
        JSONObject json = new JSONObject();
        json.put("field", "生日");
        json.put("note", "出生日期");
        json.put("active", true);
        json.put("display", true);
        String post = HttpsUtil.doPost(ADD_USER_FIELD_CONFIG, json.toJSONString());
        log.info("\nurl: {} \njson: {} \npost: {}", ADD_USER_FIELD_CONFIG, json, post);
    }

    @Test
    public void deleteUserFieldConfig() {
        JSONObject json = new JSONObject();
        json.put("uuid", "edb3b0620e6044cba7de7b50a5cd427c");
        String post = HttpsUtil.doPost(DELETE_USER_FIELD_CONFIG, json.toJSONString());
        log.info("\nurl: {} \njson: {} \npost: {}", DELETE_USER_FIELD_CONFIG, json, post);
    }

    @Test
    public void editUserFieldConfig() {
        JSONObject json = new JSONObject();
        json.put("uuid", "e29a5e7544ba4ab3a24dbca787e24f4d");
        json.put("field", "生日");
        json.put("note", "出生日期");
        json.put("active", false);
        json.put("display", false);
        String post = HttpsUtil.doPost(EDIT_USER_FIELD_CONFIG, json.toJSONString());
        log.info("\nurl: {} \njson: {} \npost: {}", EDIT_USER_FIELD_CONFIG, json, post);
    }

    @Test
    public void findUserFieldConfig() {
        JSONObject json = new JSONObject();
        json.put("field", "签名");
        json.put("note", "个性签名");
        json.put("active", false);
        json.put("display", true);
        json.put("page", 1);
        json.put("size", 10);
        String post = HttpsUtil.doPost(FIND_USER_FIELD_CONFIG, json.toJSONString());
        log.info("\nurl: {} \njson: {} \npost: {}", FIND_USER_FIELD_CONFIG, json, post);
    }

}
