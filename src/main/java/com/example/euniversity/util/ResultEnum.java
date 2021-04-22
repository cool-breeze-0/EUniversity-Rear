package com.example.euniversity.util;

import lombok.Getter;

public enum ResultEnum {
    ADD_SUCCESS(100,"添加成功");

    @Getter
    int code;

    @Getter
    String msg;

    ResultEnum(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

}
