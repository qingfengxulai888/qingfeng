package com.chat.app.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.chat.app.dao.beans.User;
import com.chat.app.dao.mapper.UserMapper;
import com.chat.app.service.LoginService;
import com.chat.app.service.UserService;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.us.base.redis.RedisCommon;
import com.us.base.util.MD5Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by xulin on 2018/11/12.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

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
     *用户注册接口
     * @param request
     * @param telephone
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "userRegister", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject userRegister(
            HttpServletRequest request,
            @RequestParam(value = "telephone") String telephone,
            @RequestParam(value = "password") String password
    ) throws Exception {
        Preconditions.checkState(!userService.isExistByTelephone(telephone),"该手机号已经注册,请前往登陆");
        User user= userService.registeUser(telephone, MD5Encryption.getMD5(password));
        return ResponseWrapper().addData(user).addMessage("操作成功").ExeSuccess();
    }

    /**
     * 判断手机号是否注册
     * @param request
     * @param telephone
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "isExistByTelephone", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject isExistByTelephone(
            HttpServletRequest request,
            @RequestParam(value = "telephone") String telephone
    ) throws Exception {

       Boolean result= userService.isExistByTelephone(telephone);
        return ResponseWrapper().addData(result).ExeSuccess();
    }


    /**
     * 账号密码登陆
     * @param request
     * @param userCode
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "loginByUserCode", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject loginByUserCode(
            HttpServletRequest request,
            @RequestParam(value = "userCode") String userCode,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "lon") String lon,
            @RequestParam(value = "lat") String lat
    ) throws Exception {
        User user = userService.findUsertByUserCode(userCode);
        //判断用户是否存在
        Preconditions.checkState(user!=null,"该账号号尚未注册,请前往注册");
        //判断账号和密码是否正确
        password = MD5Encryption.getMD5(password);
        Preconditions.checkState(password.equals(user.getPassword()), "账号或密码错误,请重新登录");
        //执行登陆操作
        String token = loginService.login(user.getId());
        User u = new User();
        u.setId(user.getId());
        u.setLastLoginTime(new Date());
        u.setLon(lon);
        u.setLat(lat);
        userService.updateUserInfo(u);
        User userInfo= userMapper.selectByPrimaryKey(u.getId());
        //返回用户基本信息和token
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("user",userInfo);
        return ResponseWrapper().addData(map).ExeSuccess();
    }

    /**
     * 重置密码
     * @param request
     * @param telephone
     * @param newpassword
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject resetPassword(
            HttpServletRequest request,
            @RequestParam(value = "telephone") String telephone,
            @RequestParam(value = "newpassword") String newpassword,
            @RequestParam(value = "lon") String lon,
            @RequestParam(value = "lat") String lat
    ) throws Exception {
        //判断用户是否存在
        Preconditions.checkState(userService.isExistByTelephone(telephone),"该手机号尚未注册,请前往注册");
        //执行用户重置密码操作
        User param = userService.findUsertByTelephone(telephone);
        User user = new User();
        user.setId(param.getId());
        user.setPassword(MD5Encryption.getMD5(newpassword));
        user.setUpdateTime(new Date());
        user.setLastLoginTime(new Date());
        user.setLon(lon);
        user.setLat(lat);
        userService.updateUserInfo(user);
        //执行登陆，并返回用户基本信息
        String token = loginService.login(param.getId());
        User userInfo= userMapper.selectByPrimaryKey(param.getId());
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("user",userInfo);
        return ResponseWrapper().addData(map).addMessage("密码设置成功").ExeSuccess();
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
