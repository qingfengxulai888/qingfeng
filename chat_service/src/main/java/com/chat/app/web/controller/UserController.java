package com.chat.app.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.chat.app.dao.beans.User;
import com.chat.app.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by zbs on 2016/12/14.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;



    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getUserInfo(HttpServletRequest request) throws Exception {
        PageInfo<User> userList=userService.search(1,2);
        return ResponseWrapper().addData(userList).ExeSuccess();
    }


    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject createActivity(
            HttpServletRequest request,
            @ModelAttribute User user
    ) throws Exception {

       userService.add(user);
        return ResponseWrapper().addMessage("操作成功").ExeSuccess();
    }


}
