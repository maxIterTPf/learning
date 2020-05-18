package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Data
@Entity
@IdClass(UserFieldLabel.IdKey.class)
public class UserFieldLabel implements Serializable {

    private static final long serialVersionUID = -7151191532304705788L;

    @Id
    private String userUuid;
    @Id
    private String fieldUuid;
    private String value;

    public static UserFieldLabel create(String userUuid, String fieldUuid) {
        UserFieldLabel userFieldLabel = new UserFieldLabel();
        userFieldLabel.setUserUuid(userUuid);
        userFieldLabel.setFieldUuid(fieldUuid);
        return userFieldLabel;
    }

    @Data
    public static class IdKey implements Serializable {

        private static final long serialVersionUID = 7596203972234803010L;

        private String userUuid;
        private String fieldUuid;

    }

}
