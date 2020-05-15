package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.Msg;

public interface LoginService {

    Msg<?> doLogin(JSONObject params);

    Msg<?> doCreate(JSONObject params);
}
