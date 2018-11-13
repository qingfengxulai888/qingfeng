package com.chat.app.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.chat.app.dao.beans.User;
import com.chat.app.dao.mapper.UserMapper;
import com.chat.app.service.LoginService;
import com.chat.app.service.UserService;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.us.base.util.MD5Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by xulin on 2018/11/13.
 */
@RestController
@RequestMapping("/friend")
public class FriendController extends BaseController {

    @Autowired
    @Qualifier(value = "userService")
    private UserService userService;

    @Autowired
    @Qualifier(value = "loginService")
    private LoginService loginService;

    @Autowired
    @Qualifier(value = "userMapper")
    private UserMapper userMapper;




    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getUserInfo(HttpServletRequest request) throws Exception {
        PageInfo<User> userList=userService.search(1,2);
        return ResponseWrapper().addData(userList).ExeSuccess();
    }

    /**
     * 根据账号精准查询
     * @param request
     * @param userCode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findByUserCode", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject findByUserCode(HttpServletRequest request,
                                     @RequestParam(value = "userCode") String userCode) throws Exception {
        User user = userService.findUsertByUserCode(userCode);
        return ResponseWrapper().addData(user).ExeSuccess();
    }

    /**
     * 导入通讯录，查询用户
     * @param request
     * @param telephones
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findByTelephone", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject findByTelephone(HttpServletRequest request,
                                     @RequestParam(value = "telephone") String[] telephones) throws Exception {
        List<User> list = new ArrayList<>();
        for (String tel:telephones) {
           User u = userService.findUsertByTelephone(tel);
           if (null != u)
               list.add(u);
        }
        return ResponseWrapper().addData(list).ExeSuccess();
    }


}
