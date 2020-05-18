package com.example.demo.entity;

import com.example.demo.util.UUIDUtil;
import lombok.Data;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = -8362687926082177182L;

    @Id
    private String uuid;
    private String account; // 用户名 可修改
    private String username;// 登录名 不可修改
    private String password;// 登录密码 不可修改
    private String salt;

    public static User create(String username, String password) {
        User user = new User();
        user.setUuid(UUIDUtil.randomUUID());
        user.setSalt(UUIDUtil.randomUUID());
        user.setUsername(username);
        user.setPassword(md5Password(password, user.getSalt()));
        user.setAccount(username);
        return user;
    }

    // md5加密 明文密码和盐
    private static String md5Password(String password, String salt) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }

    // 验证密码
    public boolean verify(String password) {
        return md5Password(password, this.salt).equals(this.password);
    }

}
