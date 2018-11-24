package com.qf.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ResultData<T> implements Serializable {
    private Integer code;
    private String msg;
    private T Data;

    public ResultData(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        Data = data;
    }
}
