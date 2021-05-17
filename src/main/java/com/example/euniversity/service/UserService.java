package com.example.euniversity.service;

import com.example.euniversity.dao.UserMapper;
import com.example.euniversity.pojo.User;
import com.example.euniversity.util.Result;
import com.example.euniversity.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private SendSmsService sendSmsService;

    @Autowired
    private UserMapper userMapper;

    public Result findUserByPhone(String phone){
        User user=userMapper.findUserByPhone(phone);
        if(user==null){
            return new Result(ResultEnum.USER_NOT_EXIST.getCode(),ResultEnum.USER_NOT_EXIST.getMsg(),null);
        }else{
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(), Arrays.asList(user));
        }
    }

    public Result register(String phone,String password){
        if(findUserByPhone(phone).getCode()==ResultEnum.FIND_SUCCESS.getCode()){
            return new Result(ResultEnum.USER_IS_EXIST.getCode(),ResultEnum.USER_IS_EXIST.getMsg(),null);
        }else if(userMapper.addUser(new User(phone,password,"null",0,"null","null","null"))==1){
            return new Result(ResultEnum.REGISTER_SUCCESS.getCode(),ResultEnum.REGISTER_SUCCESS.getMsg(),null);
        }else{
            return new Result(ResultEnum.REGISTER_FAILD.getCode(),ResultEnum.REGISTER_FAILD.getMsg(),null);
        }
    }

    public Result login(String phone,String password){
        User user=userMapper.findUserByPhone(phone);
        if(user==null){
            return new Result(ResultEnum.USER_NOT_EXIST.getCode(),ResultEnum.USER_NOT_EXIST.getMsg(),null);
        }else if(user.getPassword().equals(password)){
            return new Result(ResultEnum.LOGIN_SUCCESS.getCode(),ResultEnum.LOGIN_SUCCESS.getMsg(),null);
        }else{
            return new Result(ResultEnum.PASSWORD_IS_WRONG.getCode(),ResultEnum.PASSWORD_IS_WRONG.getMsg(),null);
        }
    }

    public Result retrievePassword(String phone,String password){
        User user=userMapper.findUserByPhone(phone);
        if(user==null){
            return new Result(ResultEnum.USER_NOT_EXIST.getCode(),ResultEnum.USER_NOT_EXIST.getMsg(),null);
        }else if(userMapper.updatePassword(phone, password)==1){
            return new Result(ResultEnum.RETRIEVE_PASSWORD_SUCCESS.getCode(),ResultEnum.RETRIEVE_PASSWORD_SUCCESS.getMsg(),null);
        }else{
            return new Result(ResultEnum.RETRIEVE_PASSWORD_FAILD.getCode(),ResultEnum.RETRIEVE_PASSWORD_FAILD.getMsg(),null);
        }
    }

    public Result changePassword(String phone,String password,String newPassword){
        User user=userMapper.findUserByPhone(phone);
        if(user==null){
            return new Result(ResultEnum.USER_NOT_EXIST.getCode(),ResultEnum.USER_NOT_EXIST.getMsg(),null);
        }else if(user.getPassword().equals(password)&&userMapper.updatePassword(phone, newPassword)==1){
            return new Result(ResultEnum.CHANGE_PASSWORD_SUCCESS.getCode(),ResultEnum.CHANGE_PASSWORD_SUCCESS.getMsg(),null);
        }else{
            return new Result(ResultEnum.PASSWORD_IS_WRONG.getCode(),ResultEnum.PASSWORD_IS_WRONG.getMsg(),null);
        }
    }

    public Result updateProfileInformation(String phone,String nikeName,int gender,String province,String city,String identity){
        User user = userMapper.findUserByPhone(phone);
        if(user==null){
            return new Result(ResultEnum.USER_NOT_EXIST.getCode(),ResultEnum.USER_NOT_EXIST.getMsg(),null);
        }else if(userMapper.updateProfileInformation(phone, nikeName, gender, province, city, identity)==1){
            return new Result(ResultEnum.UPDATE_PROFILE_INFORMATION_SUCCESS.getCode(),ResultEnum.UPDATE_PROFILE_INFORMATION_SUCCESS.getMsg(),null);
        }else{
            return new Result(ResultEnum.UPDATE_PROFILE_INFORMATION_FAILD.getCode(),ResultEnum.UPDATE_PROFILE_INFORMATION_FAILD.getMsg(),null);
        }
    }

    public Result sendSms(String phone){
        String code= UUID.randomUUID().toString().substring(0,4);
        boolean isSend= sendSmsService.send(phone,code);
        if(isSend){
            return new Result(ResultEnum.SEND_SUCCESS.getCode(),ResultEnum.SEND_SUCCESS.getMsg(),Arrays.asList(code));
        }else{
            return new Result(ResultEnum.SEND_FAILD.getCode(),ResultEnum.SEND_FAILD.getMsg(),null);
        }
    }
}
