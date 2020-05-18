package com.example.demo.entity;

import com.example.demo.util.UUIDUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class UserFieldConfig implements Serializable {

    private static final long serialVersionUID = -7871662772744411192L;

    @Id
    private String uuid;
    private String field;   // 字段名
    private String note;    // 字段说明
    private Boolean active; // 是否必填
    private Boolean display;// 是否展示

    public static UserFieldConfig create(String field) {
        UserFieldConfig userFieldConfig = new UserFieldConfig();
        userFieldConfig.setUuid(UUIDUtil.randomUUID());
        userFieldConfig.setField(field);
        return userFieldConfig;
    }

}
