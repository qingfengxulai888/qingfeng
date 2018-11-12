package com.chat.app.service;

import com.chat.app.dao.beans.User;
import com.chat.app.dao.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.us.base.util.MD5Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by DL on 2017/1/3.
 */
@Service
public class UserService {

    @Autowired
   private UserMapper userMapper;


    public PageInfo<User> search(int page, int pageSize){
        PageHelper.startPage(page,pageSize);
        List<User> userList = userMapper.getUserInfo();
        return new PageInfo<User>(userList);
    }

    @Transactional
    public  void  add(User user){
        userMapper.insert(user);
    }

    /**
     * 根据telephone判断用户是否存在
     *
     * @param telephone
     * @return 如果用户存在, 返回true;否则返回false
     */
    public boolean isExistByTelephone(String telephone) {
        Preconditions.checkArgument(User.isValidPhoneNum(telephone), "不是一个有效的电话号码");
        User userInfo = new User();
        userInfo.setTelephone(telephone);
        if (userMapper.selectCount(userInfo) > 0)
            return true;
        return false;
    }

    /**
     * 根据账号查询用户信息
     * @param userCode
     * @return
     */
    public User findUsertByUserCode(String userCode) {

        User userInfo = new User();
        userInfo.setUserCode(userCode);
        User user = userMapper.selectOne(userInfo);
        return user;
    }

    /**
     * 根据手机号查询用户信息
     * @param telephone
     * @return
     */
    public User findUsertByTelephone(String telephone) {

        User userInfo = new User();
        userInfo.setTelephone(telephone);
        User user = userMapper.selectOne(userInfo);
        return user;
    }

    /**
     * 手机号注册
     * @param telephone
     * @param password
     * @return
     * @throws Exception
     */
    public  User registeUser(String telephone, String password) throws Exception {

        User user = new User();
        user.setTelephone(telephone);
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setEnable(1);
        user.setUserCode(createUserCode());
        userMapper.insert(user);
        return  user;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     * @throws Exception
     */
    public  int updateUserInfo(User user) throws Exception {

        Preconditions.checkNotNull(user, "用户数据为空");
        return userMapper.updateByPrimaryKeySelective(user);
    }



    /**
     * 创建账号
     * @return
     * @throws Exception
     */
    private  String createUserCode() throws Exception {

        String userCode= String.valueOf(new Random().nextInt(100000 ))+String.valueOf(new Random().nextInt(10000 ));
        User userInfo = new User();
        userInfo.setUserCode(userCode);
        if (userMapper.selectCount(userInfo) > 0 ){
            createUserCode();
        }
        return  userCode;
    }




}
