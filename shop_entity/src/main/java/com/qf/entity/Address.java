package com.qf.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Address implements Serializable {

    private Integer id;
    private String person;
    private String address;
    private String phone;
    private String code;
    private Integer uid;
    private Integer isdefault;

}
