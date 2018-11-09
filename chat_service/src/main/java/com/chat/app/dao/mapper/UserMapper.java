package com.chat.app.dao.mapper;

import com.chat.app.dao.beans.User;
import com.us.base.mybatis.base.MyMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据telephone查找一个用户(telephone已唯一约束)
     *
     * @param telephone
     * @return
     */
    @Select({
            "select",
           /* "id, open_id,telephone,password,default_address,ext,create_time,last_login_time,has_telephone",*/
            "*",
            "from user",
            "where telephone = #{telephone,jdbcType=VARCHAR}"
    })
    User selectByTelephone(String telephone);

    @Update({
            "update",
            "user",
            "SET",
            "default_address = #{defaultAddress,jdbcType=INTEGER}",
            "WHERE id=#{id,jdbcType=BIGINT}"
    })
    void updateDefaultAddress(User userInfo);


    /**
     * 修改最后登录时间
     * @param id
     */
    @Update({
            "update `user` set last_login_time=NOW() where id = #{id}"
    })
    void updateLastLoginTime(Long id);

    /**
     * 根据openId查找一个用户(openId唯一约束)
     * @param openId
     * @return
     */
    @Select({
            "select",
            "*",
            "from user",
            "where open_id = #{openId,jdbcType=VARCHAR}"
    })
    User selectByOpenId(String openId);


    @Select({
            "SELECT * FROM user"
    })
    List<User> selectUserInfo();

    @Select({
            "SELECT * FROM user WHERE id = #{id}"
    })
    User findUserInfoById(@Param("id") Long id);

    /**
     * 新增参加活动的用户
     * @param userName
     * @param telephone
     * @param activityName
     */
    @Insert("INSERT INTO  `activity_user`( `user_name` ,`telephone`,`activity_name`,area ) VALUES(#{param1},#{param2},#{param3},#{param4})")
    void addActivityUser(String userName, String telephone, String activityName, String area);


    /**
     * 根据手机号查询,判断该手机号是否已经报名
     * @param telephone
     * @return
     */
    @Select("SELECT * FROM  `activity_user`  WHERE  `telephone` =#{param1}  and activity_name=#{param2}")
    @ResultType(Map.class)
    List<Map<String,Object>>  findByTelephone(String telephone, String activity_name);


    /**
     *
     * @param id  主键
     * @param meScoure   我的分数
     * @param youScoure     对方的分数
     * @param meImageUrl    我的照片地址
     * @param youImageUrl   对方的照片地址
     * @return
     */
    @Insert("INSERT INTO `weixin_pk`(`id` ,`meScoure` ,`youScoure` ,`meImageUrl` ,`youImageUrl` ) VALUES(#{param1},#{param2},#{param3},#{param4},#{param5})")
    Integer  addWeiXinPK(String id, Integer meScoure, Integer youScoure, String meImageUrl, String youImageUrl);



    @Update("update weixin_pk set meScoure=#{param2} , youScoure=#{param3} , meImageUrl=#{param4} , youImageUrl=#{param5} where id = #{param1} ")
    Integer updateWeiXinPK(String id, Integer meScoure, Integer youScoure, String meImgUrl, String youImgUrl);




    /**
     * 根据id查出颜值pk分享数据
     * @param
     * @return
     */
    @Select("select * from weixin_pk where id = #{param1}")
    @ResultType(Map.class)
    Map<String , Object> weixinSelectById(String id);






    /**
     * 根据手机号去数据库表中查询是否有该手机注册的用户
     */
    @Select("select * from award_pk where p_user = #{param1}")
    @ResultType(Map.class)
    Map<String , Object> weixinPKActivityUserByPhone(String phone);



    /**
     * 向数据表中插入数据
     * type玻尿酸（优惠券）   数量1  用户=手机号   地区=北京（上海） 操作=领取成功(2)    操作时间=now()
     */

    @Insert("insert into award_pk  (p_name , p_number , p_user , area , operation , operating_time)  values (#{param1} , 1 ,#{param2} , #{param3} , 2 , now())  ")
     Integer addWeiXinPKActivityUser(String type, String phone, String cityName);


    /** 获取指定城市的玻尿酸初始数据量
     *字段 operation  1:增加库存   2:领取成功
     */
    @Select("select p_number  from    award_pk     where   area=#{param1}   and   operation=1 and p_name='玻尿酸' ")
    @ResultType(Map.class)
     Map<String , Object> getHATotal(String cityName);


    /**
     * 获取指定城市的指定类型(玻尿酸)领取总量
     */
     @Select("select count(id) as p_sum from   award_pk  where area=#{param1} and operation=2 and p_name='玻尿酸'")
     @ResultType(Map.class)
    Map<String , Object> getHASum(String cityName);







}
