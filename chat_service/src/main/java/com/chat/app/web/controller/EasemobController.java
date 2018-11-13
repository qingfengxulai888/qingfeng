package com.chat.app.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.chat.app.dao.mapper.UserMapper;
import com.chat.app.service.LoginService;
import com.chat.app.service.UserService;
import com.chat.app.util.easemob.api.impl.EasemobIMUsers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import io.swagger.client.model.RegisterUsers;


import javax.servlet.http.HttpServletRequest;


/**
 * Created by xulin on 2018/11/12.
 */
@RestController
@RequestMapping("/easemob")
public class EasemobController extends BaseController {

    private static final Log logger = LogFactory.getLog(EasemobController.class);
    private static final Gson gson = new GsonBuilder().serializeNulls().create();
    private final static EasemobIMUsers easemobIMUsers = new EasemobIMUsers();


    @Autowired
    @Qualifier(value = "userService")
    private UserService userService;

    @Autowired
    @Qualifier(value = "loginService")
    private LoginService loginService;

    @Autowired
    @Qualifier(value = "userMapper")
    private UserMapper userMapper;




    /**
     *环信创建用户接口
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "userRegister", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject userRegister(HttpServletRequest request) throws Exception {

        RegisterUsers registerUsers = new RegisterUsers();
        io.swagger.client.model.User p = new io.swagger.client.model.User().username("pangpang").password("111111");
        registerUsers.add(p);
        Object result = easemobIMUsers.createNewIMUserSingle(registerUsers);
        logger.info(gson.toJson(result));

        return ResponseWrapper().addData("success").addMessage("环信创建用户操作成功").ExeSuccess();
    }



}
