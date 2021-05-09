package com.example.euniversity.service;

import com.example.euniversity.dao.CommunityMapper;
import com.example.euniversity.pojo.Answer;
import com.example.euniversity.pojo.Problem;
import com.example.euniversity.pojo.ProblemAnswer;
import com.example.euniversity.util.Result;
import com.example.euniversity.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityService {
    @Autowired
    private CommunityMapper communityMapper;

    public Result findAllProblemAnswer(){
        List<ProblemAnswer> problemAnswers = new ArrayList<ProblemAnswer>();
        List<Problem> problems=communityMapper.findAllProblem();
        if(problems.size()==0){
            return new Result(ResultEnum.NO_PROBLEMS_IN_DATABASE.getCode(),ResultEnum.NO_PROBLEMS_IN_DATABASE.getMsg(),null);
        }else{
            for(int i=0;i<problems.size();i++){
                List<Answer> answers=communityMapper.findAnswerByProblemId(problems.get(i).getId());
                problemAnswers.add(new ProblemAnswer(problems.get(i),answers));
            }
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(),problemAnswers);
        }
    }

    public Result askProblem(String content, LocalDate time, String userPhone){
        if(communityMapper.addProblem(content,time,userPhone)==1){
            return new Result(ResultEnum.ASK_PROBLEM_SUCCESS.getCode(),ResultEnum.ASK_PROBLEM_SUCCESS.getMsg(),null);
        }else{
            return new Result(ResultEnum.ASK_PROBLEM_FAILD.getCode(),ResultEnum.ASK_PROBLEM_FAILD.getMsg(),null);
        }
    }
}
