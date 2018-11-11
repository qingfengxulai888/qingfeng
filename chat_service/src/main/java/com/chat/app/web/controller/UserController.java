package com.chat.app.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.chat.app.dao.beans.User;
import com.chat.app.service.UserService;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.us.base.redis.RedisCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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

    RedisCommon.push("xulin","111111");
        return ResponseWrapper().addMessage("操作成功").ExeSuccess();
    }

    @RequestMapping(value = "testRedis", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject testRedis(HttpServletRequest request) throws Exception {

            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(10);
            jedisPoolConfig.setMaxTotal(1);
            jedisPoolConfig.setMaxWaitMillis(1000*10);
            jedisPoolConfig.setMinEvictableIdleTimeMillis(18000);
            jedisPoolConfig.setMinIdle(0);
            jedisPoolConfig.setTestOnBorrow(true);
            jedisPoolConfig.setTestWhileIdle(true);

        JedisPool  jedisPool =  new JedisPool(jedisPoolConfig,"47.104.129.142",6379,10000,"111111");
       // JedisPool jedisPool = new JedisPool("47.104.129.142",6379);

        Jedis jedis=jedisPool.getResource();
        jedis.set("zhangsanfeng","zhangsanfeng");
        System.out.print(jedis.get("zhangsanfeng"));





        return ResponseWrapper().addMessage("操作成功").ExeSuccess();
    }

    /**
     * 验证码密码登录
     * @param telephone
     * @param validCode
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "loginfromweb",method = RequestMethod.POST)
    @ResponseBody
    public  JSONObject loginfromweb( @RequestParam(value = "telephone") String telephone,
                                     @RequestParam(value = "validCode") String validCode,
                                     @RequestParam(value = "password") String password)throws  Exception{

        Preconditions.checkState(User.isValidPhoneNum(telephone), "手机格式不符合");
        return  null;

    }


}
