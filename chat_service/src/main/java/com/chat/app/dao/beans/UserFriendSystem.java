package com.chat.app.dao.beans;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_user_friend_system")
public class UserFriendSystem {

    @Id
    private Long id;

    private  Long userId;

    private  Long userToId;
    /**
     * 状态（1发送请求2待验证3已加入好友4已删除5已拉黑）
     */
    private  Integer status;

    private Integer enable;
}