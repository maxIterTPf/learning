package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.Msg;

public interface UserService {

    Msg<?> addUserFieldConfig(JSONObject params);

    Msg<?> deleteUserFieldConfig(JSONObject params);

    Msg<?> editUserFieldConfig(JSONObject params);

    Msg<?> findUserFieldConfig(JSONObject params);

    Msg<?> editUserFieldLabel(JSONObject params);

    Msg<?> findUserFieldLabel(JSONObject params);

}
