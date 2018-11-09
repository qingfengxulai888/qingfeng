package com.chat.app.service;

import com.chat.app.dao.beans.User;
import com.chat.app.dao.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
