package com.qf.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private Date birthday;
    private String idcard;
    private String phone;
    private String email;
    private String token;
    private String time;
}
