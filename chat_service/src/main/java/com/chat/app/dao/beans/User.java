package com.chat.app.dao.beans;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_user")
public class User {

    @Id
    private Long id;

    private String name;

    private Integer age;

}