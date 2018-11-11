package com.chat.app.service;

import com.chat.app.dao.beans.User;
import com.chat.app.dao.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
     * 手机号注册
     * @param telephone
     * @param password
     * @return
     * @throws Exception
     */
    public  User registeUser(String telephone, String password) throws Exception {
        User user = new User();
        user.setTelephone(telephone);
        user.setUserPassword(password);
        user.setCreateTime(new Date());
        user.setEnable(1);
        userMapper.insert(user);
        return  user;
    }


}
