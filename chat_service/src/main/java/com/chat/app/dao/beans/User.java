package com.chat.app.dao.beans;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_user")
public class User {

    @Id
    private Long id;

    private String userCode;

    private Integer age;

    private String userPassword;

    private String telephone;

    private  Integer gender;

    private  String signature;

    private  String nickName;

    private  String headPortrait;

    private  Date createTime;

    private  Date updateTime;

    private Date lastLoginTime;

    private  String lon;

    private  String lat;

    private Integer enable;
}