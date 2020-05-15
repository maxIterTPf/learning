package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.Msg;

public interface UserService {

    Msg<?> addUserFieldConfig(JSONObject params);

    Msg<?> editUserFieldConfig(JSONObject params);
}
