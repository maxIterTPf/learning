package com.example.demo;

import org.springframework.util.DigestUtils;

import java.util.UUID;

public class Test {

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
        System.out.println(DigestUtils.md5DigestAsHex("MessageDigest MD5".getBytes()));
    }
}
