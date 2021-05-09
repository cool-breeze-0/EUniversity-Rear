package com.example.euniversity.controller;

import com.example.euniversity.pojo.Problem;
import com.example.euniversity.service.CommunityService;
import com.example.euniversity.util.Result;
import com.example.euniversity.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/community")
public class CommunityController {
    @Autowired
    private CommunityService communityServce;

    @GetMapping(value = "/findAllProblemAnswer",produces = "application/json;charset=UTF-8")
    public Result findAllProblemAnswer(){
        return communityServce.findAllProblemAnswer();
    }

    @PostMapping(value = "/askProblem",produces = "application/json;charset=UTF-8")
    public Result askProblem(String content, String time,String userPhone){
        if(content.equals("")||userPhone.equals("")||time==null){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return communityServce.askProblem(content,LocalDate.parse(time),userPhone);
        }
    }
}
