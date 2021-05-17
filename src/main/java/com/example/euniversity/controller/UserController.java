package com.example.euniversity.controller;

import com.example.euniversity.dao.UserMapper;
import com.example.euniversity.pojo.User;
import com.example.euniversity.service.UserService;
import com.example.euniversity.util.Result;
import com.example.euniversity.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/findUserByPhone",produces = "application/json;charset=UTF-8")
    public Result findUserByPhone(String phone){
        return userService.findUserByPhone(phone);
    }

    @PostMapping(value="/register", produces = "application/json;charset=UTF-8")
    public Result register(String phone,String password){
        if(phone.equals("")||password.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else {
            return userService.register(phone, password);
        }
    }

    @PostMapping(value = "/login",produces = "application/json;charset=UTF-8")
    public Result login(String phone,String password){
        if(phone.equals("")||password.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return userService.login(phone,password);
        }
    }

    @PostMapping(value = "/retrievePassword",produces = "application/json;charset=UTF-8")
    public Result retrievePassword(String phone,String password){
        if(phone.equals("")||password.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return userService.retrievePassword(phone, password);
        }
    }

    @PostMapping(value = "/changePassword",produces = "application/json;charset=UTF-8")
    public Result changePassword(String phone,String password,String newPassword){
        if(phone.equals("")||password.equals("")||newPassword.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return userService.changePassword(phone, password, newPassword);
        }
    }

    @PostMapping(value = "/updateProfileInformation",produces = "application/json;charset=UTF-8")
    public Result updateProfileInformation(String phone,String nikeName,int gender,String province,String city,String identity){
        if(phone.equals("")||nikeName.equals("")||province.equals("")||city.equals("")||identity.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return userService.updateProfileInformation(phone, nikeName, gender, province, city, identity);
        }
    }

    @GetMapping(value = "/sendSms",produces = "application/json;charset=UTF-8")
    public Result sendSms(String phone){
        if(phone.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return userService.sendSms(phone);
        }
    }
}
