package com.example.euniversity.controller;

import com.example.euniversity.dao.UserMapper;
import com.example.euniversity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/addUser")
    private String addUser(){
        userMapper.addUser(new User("15079152561","456654","helloworld",0,"江西省","南昌市","在校大学生"));
        return "addUser success";
    }
}
