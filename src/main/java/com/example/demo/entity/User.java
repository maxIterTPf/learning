package com.example.demo.entity;

import lombok.Data;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = -8362687926082177182L;
    @Id
    private String uuid;
    private String account;
    private String username;
    private String password;
    private String salt;

    public static User create(String username, String password) {
        User user = new User();
        user.setUuid(randomUUID());
        user.setSalt(randomUUID());
        user.setUsername(username);
        user.setPassword(md5Password(password, user.getSalt()));
        user.setAccount(username);
        return user;
    }

    // md5加密 明文密码和盐
    private static String md5Password(String password, String salt) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }

    // uuid
    private static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public boolean verify(String password) {
        return md5Password(password, this.salt).equals(this.password);
    }
}
