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
        if(content.equals("")||userPhone.equals("")||time.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return communityServce.askProblem(content,LocalDate.parse(time),userPhone);
        }
    }

    @GetMapping(value = "/myProblem",produces = "application/json;charset=UTF-8")
    public Result myProblem(String userPhone){
        if(userPhone.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return communityServce.myProblem(userPhone);
        }
    }

    @GetMapping(value = "/myAnswer",produces = "application/json;charset=UTF-8")
    public Result myAnswer(String userPhone){
        if(userPhone.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return communityServce.myAnswer(userPhone);
        }
    }

    @GetMapping(value = "/commonProblem",produces = "application/json;charset=UTF-8")
    public Result commonProblem(){
        return communityServce.commonProblem();
    }

    @GetMapping(value = "/qualitySortProblem",produces = "application/json;charset=UTF-8")
    public Result qualitySortProblem(){
        return communityServce.qualitySortProblem();
    }

    @GetMapping(value = "/comprehensiveSortProblem",produces = "application/json;charset=UTF-8")
    public Result comprehensiveSortProblem(){
        return communityServce.comprehensiveSortProblem();
    }

    @GetMapping(value = "/searchProblem",produces = "application/json;charset=UTF-8")
    public Result searchProblem(String text){
        if(text.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return communityServce.searchProblem(text);
        }
    }

    @GetMapping(value = "/isliked",produces = "application/json;charset=UTF-8")
    public Result isliked(String userPhone,int answerId){
        if(userPhone.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return communityServce.isliked(userPhone,answerId);
        }
    }

    @GetMapping(value = "/changeToLiked",produces = "application/json;charset=UTF-8")
    public Result changeToLiked(String userPhone,int answerId){
        if(userPhone.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return communityServce.changeToLiked(userPhone,answerId);
        }
    }

    @GetMapping(value = "/changeToUnliked",produces = "application/json;charset=UTF-8")
    public Result changeToUnliked(String userPhone,int answerId){
        if(userPhone.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return communityServce.changeToUnliked(userPhone,answerId);
        }
    }

    @GetMapping(value = "/updateAnswerLikes",produces = "application/json;charset=UTF-8")
    public Result updateAnswerLikes(int answerId,int likes){
        return communityServce.updateAnswerLikes(answerId,likes);
    }

    @GetMapping(value = "/findProblemById",produces = "application/json;charset=UTF-8")
    public Result findProblemById(int problemId){
        return communityServce.findProblemById(problemId);
    }

    @PostMapping(value = "/answerProblem",produces = "application/json;charset=UTF-8")
    public Result answerProblem(String content,String time,String userPhone,int problemId){
        if(content.equals("")||userPhone.equals("")||time.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return communityServce.answerProblem(content,LocalDate.parse(time),userPhone,problemId);
        }
    }

    @PostMapping(value = "/updateAnswer",produces = "application/json;charset=UTF-8")
    public Result updateAnswer(int answerId,String content,String time){
        if(content.equals("")||time.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return communityServce.updateAnswer(answerId,content,LocalDate.parse(time));
        }
    }
}
