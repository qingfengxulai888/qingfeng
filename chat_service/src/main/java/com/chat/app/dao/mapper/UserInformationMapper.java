package com.chat.app.dao.mapper;

import com.chat.app.dao.beans.User;
import com.us.base.mybatis.base.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by righteyte on 16/6/16.
 */
public interface UserInformationMapper extends MyMapper<User> {


    @Select({

            "SELECT * FROM tb_user WHERE id = #{id}"
    })
    User findUserInfomationById(@Param("id") Long id);
}
