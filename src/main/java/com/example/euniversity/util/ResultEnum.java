package com.example.euniversity.util;

import lombok.Getter;

public enum ResultEnum {
    REGISTER_SUCCESS(200,"注册成功"),
    FIND_SUCCESS(201,"查询成功"),
    LOGIN_SUCCESS(202,"登陆成功"),
    RETRIEVE_PASSWORD_SUCCESS(203,"找回密码成功"),
    CHANGE_PASSWORD_SUCCESS(203,"修改密码成功"),
    UPDATE_PROFILE_INFORMATION_SUCCESS(204,"更新个人信息成功"),
    ASK_PROBLEM_SUCCESS(205,"提交问题成功"),
    LIKE_EXIST(206,"该用户已点赞"),
    ADD_LIKE_SUCCESS(207,"添加点赞成功"),
    DEL_LIKE_SUCCESS(208,"删除点赞成功"),
    UPDATE_ANSWER_LIKES_SUCCESS(209,"更新回答点赞量成功"),
    ADD_ANSWER_SUCCESS(210,"添加回答成功"),
    UPDATE_ANSWER_SUCCESS(211,"修改回答成功"),
    SEND_SUCCESS(212,"发送成功"),
    USER_NOT_EXIST(400,"用户不存在"),
    USER_IS_EXIST(401,"用户已存在"),
    REGISTER_FAILD(402,"注册失败"),
    INPUT_IS_NULL(403,"提交数据为空"),
    PASSWORD_IS_WRONG(404,"密码错误"),
    RETRIEVE_PASSWORD_FAILD(405,"修改密码失败"),
    UPDATE_PROFILE_INFORMATION_FAILD(406,"更新个人信息失败"),
    NO_PROBLEMS_IN_DATABASE(407,"数据库中没有更多问题数据"),
    ASK_PROBLEM_FAILD(408,"提交问题失败"),
    USER_PROBLEM_NOT_EXIST(409,"用户没有提问"),
    USER_ANSWER_NOT_EXIST(410,"用户没有回答问题"),
    FIND_PROBLEM_FAILE(411,"没有匹配到问题"),
    LIKE_NOT_EXIST(412,"该用户未点赞"),
    ADD_LIKE_FAILD(413,"添加点赞失败"),
    DEL_LIKE_FAILD(414,"删除点赞失败"),
    UPDATE_ANSWER_LIKES_FAILD(415,"更新回答点赞量失败"),
    PROBLEM_NOT_EXIST(416,"问题不存在"),
    ADD_ANSWER_FAILD(417,"添加回答失败"),
    UPDATE_ANSWER_FAILD(418,"添加回答失败"),
    NO_UNIVERSITYS_IN_DATABASE(419,"数据库中没有学校数据"),
    FIND_UNIVERSITY_FAILE(420,"没有匹配到学校"),
    FILTER_UNIVERSITY_FAILE(421,"未筛选到学校"),
    UNIVERSITY_NOT_EXIST(422,"学校不存在"),
    SEND_FAILD(423,"发送失败");

    @Getter
    int code;

    @Getter
    String msg;

    ResultEnum(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

}
