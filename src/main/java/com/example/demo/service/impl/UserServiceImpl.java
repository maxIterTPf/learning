package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.Msg;
import com.example.demo.dto.Page;
import com.example.demo.entity.UserFieldConfig;
import com.example.demo.entity.UserFieldLabel;
import com.example.demo.repository.UserFieldConfigRepository;
import com.example.demo.repository.UserFieldLabelRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserFieldConfigRepository userFieldConfigRepository;
    @Autowired
    private UserFieldLabelRepository userFieldLabelRepository;
    @Autowired
    private UserRepository userRepository;

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
    public Msg<?> deleteUserFieldConfig(JSONObject params) {
        if (params == null) {
            return Msg.errMsg("参数为空");
        }
        String uuid = params.getString("uuid");
        if (StringUtils.isEmpty(uuid)) {
            return Msg.errMsg("UUID为空");
        }
        if (!userFieldConfigRepository.existsById(uuid)) {
            return Msg.errMsg("字段不存在");
        }
        userFieldConfigRepository.deleteById(uuid);
        return Msg.sucMsg(null);
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
            return Msg.errMsg("字段名已存在");
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

    @Override
    public Msg<?> findUserFieldConfig(JSONObject params) {
        if (params == null) {
            Page<UserFieldConfig> page = Page.getInstance(1, 10);
            return Msg.sucMsg(page.saveData(userFieldConfigRepository.findAll(PageRequest.of(page.searchPage(), page.searchSize()))));
        }
        Page<UserFieldConfig> page = Page.getInstance(params.getIntValue("page"), params.getIntValue("size"));
        PageRequest pageRequest = PageRequest.of(page.searchPage(), page.searchSize());
        UserFieldConfig userFieldConfig = new UserFieldConfig();
        userFieldConfig.setField(params.getString("field"));
        userFieldConfig.setNote(params.getString("note"));
        userFieldConfig.setActive(params.getBoolean("active"));
        userFieldConfig.setDisplay(params.getBoolean("display"));
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("field", ExampleMatcher.GenericPropertyMatchers.contains());
        return Msg.sucMsg(page.saveData(userFieldConfigRepository.findAll(Example.of(userFieldConfig, matcher), pageRequest)));
    }

    @Override
    public Msg<?> editUserFieldLabel(JSONObject params) {
        if (params == null) {
            return Msg.errMsg("参数为空");
        }
        String userUuid = params.getString("userUuid");
        if (StringUtils.isEmpty(userUuid)) {
            return Msg.errMsg("账号UUID为空");
        }
        if (!userRepository.existsById(userUuid)) {
            return Msg.errMsg("账号不存在");
        }
        String fieldUuid = params.getString("fieldUuid");
        if (StringUtils.isEmpty(fieldUuid)) {
            return Msg.errMsg("字段UUID为空");
        }
        if (!userFieldConfigRepository.existsById(fieldUuid)) {
            return Msg.errMsg("字段不存在");
        }
        UserFieldLabel userFieldLabel = UserFieldLabel.create(userUuid, fieldUuid);
        userFieldLabel.setValue(params.getString("value"));
        return Msg.sucMsg(userFieldLabelRepository.save(userFieldLabel));
    }

    @Override
    public Msg<?> findUserFieldLabel(JSONObject params) {
        return null;
    }

}
