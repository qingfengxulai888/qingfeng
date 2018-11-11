package com.chat.app.dao.beans;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_user")
public class User {

    public static final int MAX_PASSWORD_LENGTH = 21;
    public static final int MIN_PASSWORD_LENGTH = 5;

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

    public static boolean isValidPwd(String password) {
        if (null == password) {
            return false;
        }
        return password.length() > User.MIN_PASSWORD_LENGTH && password.length() < User.MAX_PASSWORD_LENGTH;
    }

    public static boolean isValidPhoneNum(String telephone) {
        if (null == telephone) {
            return false;
        }
        return telephone.matches("^1[3-5|8|7]\\d{9}$");
    }
}