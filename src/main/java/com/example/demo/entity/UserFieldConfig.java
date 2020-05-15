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
    private String field;
    private String note;
    private Boolean active;
    private Boolean display;

    public static UserFieldConfig create(String field) {
        UserFieldConfig userFieldConfig = new UserFieldConfig();
        userFieldConfig.setUuid(UUIDUtil.randomUUID());
        userFieldConfig.setField(field);
        return userFieldConfig;
    }
}
