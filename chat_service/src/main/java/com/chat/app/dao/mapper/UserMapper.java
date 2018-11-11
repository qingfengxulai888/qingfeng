package com.chat.app.dao.mapper;

import com.chat.app.dao.beans.User;
import com.us.base.mybatis.base.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by righteyte on 16/6/16.
 */
@Mapper
@Component(value = "userMapper")
public interface UserMapper extends MyMapper<User> {


    @Select({
            "select * from tb_user "
    })
    List<User> getUserInfo();









}
