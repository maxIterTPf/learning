package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.Msg;
import com.example.demo.entity.UserFieldConfig;
import com.example.demo.repository.UserFieldConfigRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserFieldConfigRepository userFieldConfigRepository;


    @Override
    public Msg<?> addUserFieldConfig(JSONObject params) {
        if (params == null) {
            return Msg.errMsg("参数为空");
        }
        String field = params.getString("field");
        if (StringUtils.isEmpty(field)) {
            return Msg.errMsg("字段名为空");
        }
        long count = userFieldConfigRepository.countByField(field);
        if (count >= 1) {
            return Msg.errMsg("该字段已存在");
        }
        UserFieldConfig userFieldConfig = UserFieldConfig.create(field);
        userFieldConfig.setNote(params.getString("note"));
        userFieldConfig.setActive(params.getBooleanValue("active"));
        userFieldConfig.setDisplay(params.getBooleanValue("display"));
        return Msg.sucMsg(userFieldConfigRepository.save(userFieldConfig));
    }

    @Override
    public Msg<?> editUserFieldConfig(JSONObject params) {
        if (params == null) {
            return Msg.errMsg("参数为空");
        }
        String uuid = params.getString("uuid");
        if (StringUtils.isEmpty(uuid)) {
            return Msg.errMsg("UUID为空");
        }
        UserFieldConfig userFieldConfig = userFieldConfigRepository.findById(uuid).orElse(null);
        if (userFieldConfig == null) {
            return Msg.errMsg("字段不存在");
        }
        String field = params.getString("field");
        if (StringUtils.isEmpty(field)) {
            return Msg.errMsg("字段名为空");
        }
        long count = userFieldConfigRepository.countByField(field);
        if (count > 1) {
            return Msg.errMsg("当前字段重复，无法修改");
        }
        if (count < 1) {
            userFieldConfig.setField(field);
            userFieldConfig.setNote(params.getString("note"));
            userFieldConfig.setActive(params.getBooleanValue("active"));
            userFieldConfig.setDisplay(params.getBooleanValue("display"));
            return Msg.sucMsg(userFieldConfigRepository.save(userFieldConfig));
        }
        if (userFieldConfig.getField().equals(field)) {
            userFieldConfig.setNote(params.getString("note"));
            userFieldConfig.setActive(params.getBooleanValue("active"));
            userFieldConfig.setDisplay(params.getBooleanValue("display"));
            return Msg.sucMsg(userFieldConfigRepository.save(userFieldConfig));
        }
        return Msg.errMsg("当前字段名重复，无法修改");
    }
}
