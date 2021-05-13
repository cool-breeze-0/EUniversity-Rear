package com.example.euniversity.service;

import com.example.euniversity.dao.CommunityMapper;
import com.example.euniversity.pojo.Answer;
import com.example.euniversity.pojo.Like;
import com.example.euniversity.pojo.Problem;
import com.example.euniversity.pojo.ProblemAnswer;
import com.example.euniversity.util.Result;
import com.example.euniversity.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CommunityService {
    @Autowired
    private CommunityMapper communityMapper;

    //内部类，实现comparable接口用来给问题排序
    class SortProblem implements Comparable<SortProblem>{
        ProblemAnswer problemAnswer;
        int sort;  //排序指标

        public SortProblem(ProblemAnswer problemAnswer, int sort) {
            this.problemAnswer = problemAnswer;
            this.sort = sort;
        }

        //按排序指标进行排序
        @Override
        public int compareTo(SortProblem o) {
            return (o.sort < this.sort) ? -1 : ((o.sort == this.sort) ? 0 : 1);
        }
    }

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

    public Result myProblem(String userPhone){
        List<ProblemAnswer> problemAnswers=new ArrayList<>();
        List<Problem> problems=communityMapper.findUserProblem(userPhone);
        if(problems.size()==0){
            return new Result(ResultEnum.USER_PROBLEM_NOT_EXIST.getCode(),ResultEnum.USER_PROBLEM_NOT_EXIST.getMsg(),null);
        }else{
            for (Problem problem:problems) {
                List<Answer> answers=communityMapper.findAnswerByProblemId(problem.getId());
                problemAnswers.add(new ProblemAnswer(problem,answers));
            }
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(),problemAnswers);
        }
    }

    public Result myAnswer(String userPhone){
        List<ProblemAnswer> problemAnswers=new ArrayList<>();
        List<Answer> userAnswers=communityMapper.findAnswerByUserPhone(userPhone);
        if(userAnswers.size()==0){
            return new Result(ResultEnum.USER_ANSWER_NOT_EXIST.getCode(),ResultEnum.USER_ANSWER_NOT_EXIST.getMsg(),null);
        }else{
            List<Problem> problems=new ArrayList<>();
            for (Answer userAnswer:userAnswers) {
                problems.add(communityMapper.findProblemById(userAnswer.getProblemId()));
            }
            for (Problem problem:problems) {
                List<Answer> answers=communityMapper.findAnswerByProblemId(problem.getId());
                problemAnswers.add(new ProblemAnswer(problem,answers));
            }
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(),problemAnswers);
        }
    }

    /**
     * 返回前六个点赞量总和最多的问题,当数据库中问题量小于等于6个时，直接将全部问题返回
     */
    public Result commonProblem(){
        List<ProblemAnswer> problemAnswers = new ArrayList<ProblemAnswer>();
        List<ProblemAnswer> commonProblemAnswers;
        List<Problem> problems=communityMapper.findAllProblem();
        if(problems.size()==0){
            return new Result(ResultEnum.NO_PROBLEMS_IN_DATABASE.getCode(),ResultEnum.NO_PROBLEMS_IN_DATABASE.getMsg(),null);
        }else{
            for(int i=0;i<problems.size();i++){
                List<Answer> answers=communityMapper.findAnswerByProblemId(problems.get(i).getId());
                problemAnswers.add(new ProblemAnswer(problems.get(i),answers));
            }
            commonProblemAnswers=findCommonProblemInAllProblem(problemAnswers);
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(),commonProblemAnswers);
        }
    }

    /**
     * 将查询出的所有问题按回答的点赞量总和排序，返回前六个点赞量总和最多的问题
     * 当数据库中问题量小于等于6个时，直接全部返回
     * @param problemAnswers    数据库中查询的问题及回答列表
     * @return
     */
    public List<ProblemAnswer> findCommonProblemInAllProblem(List<ProblemAnswer> problemAnswers){
        if(problemAnswers.size()<=6)
            return problemAnswers;
        else{
            ArrayList<SortProblem> sortProblems=new ArrayList<>();
            List<ProblemAnswer> commonProblemAnswers=new ArrayList<ProblemAnswer>();
            //整合问题及问题的所有点赞量总和生成对应的SortProblem对象添加到列表中
            for (ProblemAnswer problemAnswer:problemAnswers) {
                int likes=0;//问题的所有点赞量总和
                for(Answer answer:problemAnswer.getAnswers()){
                    likes+=answer.getLikes();
                }
                sortProblems.add(new SortProblem(problemAnswer,likes));
            }
            //排序并返回前6个问题，逆序返回即问题的所有点赞量总和的值更大的在后面，
            // 这样返回到前端会再次逆序（默认的时间排序显示是直接将数据库中查询的所有问题逆序实现的，数据库提问时间越早越在前面）
            // 显示，就可以将问题的所有点赞量总和的值更大的放在前面了
            Collections.sort(sortProblems);
            for (int i=5;i>=0;i--){
                commonProblemAnswers.add(sortProblems.get(i).problemAnswer);
            }
            return commonProblemAnswers;
        }
    }

    /**
     *将所有问题按问题的回答的点赞量的最大值排序
     * @return
     */
    public Result qualitySortProblem(){
        List<ProblemAnswer> problemAnswers = new ArrayList<ProblemAnswer>();
        List<ProblemAnswer> qualitySortProblemAnswers;
        List<Problem> problems=communityMapper.findAllProblem();
        if(problems.size()==0){
            return new Result(ResultEnum.NO_PROBLEMS_IN_DATABASE.getCode(),ResultEnum.NO_PROBLEMS_IN_DATABASE.getMsg(),null);
        }else{
            for(int i=0;i<problems.size();i++){
                List<Answer> answers=communityMapper.findAnswerByProblemId(problems.get(i).getId());
                problemAnswers.add(new ProblemAnswer(problems.get(i),answers));
            }
            qualitySortProblemAnswers=qualitySortAllProblem(problemAnswers);
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(),qualitySortProblemAnswers);
        }
    }

    /**
     * 将所有问题按问题的回答的点赞量的最大值排序，输出结果
     * @param problemAnswers 数据库中查询的问题及回答列表
     */
    public List<ProblemAnswer> qualitySortAllProblem(List<ProblemAnswer> problemAnswers){
        ArrayList<SortProblem> sortProblems=new ArrayList<>();
        List<ProblemAnswer> qualitySortProblemAnswers=new ArrayList<ProblemAnswer>();
        //整合问题及问题的回答的点赞量的最大值生成对应的SortProblem对象添加到列表中
        for (ProblemAnswer problemAnswer:problemAnswers) {
            int likes=0;//问题的回答的点赞量的最大值
            for(Answer answer:problemAnswer.getAnswers()){
                if(answer.getLikes()>likes) likes=answer.getLikes();
            }
            sortProblems.add(new SortProblem(problemAnswer,likes));
        }
        //排序并返回结果，逆序返回即问题的回答的点赞量的最大值更大的在后面，
        // 这样返回到前端会再次逆序（默认的时间排序显示是直接将数据库中查询的所有问题逆序实现的，数据库提问时间越早越在前面）
        // 显示，就可以将问题的回答的点赞量的最大值更大的放在前面了
        Collections.sort(sortProblems);
        for(int i=sortProblems.size()-1;i>=0;i--){
            qualitySortProblemAnswers.add(sortProblems.get(i).problemAnswer);
        }
        return qualitySortProblemAnswers;
    }

    /**
     * 将所有问题按时间和问题的回答的点赞量的最大值综合排序
     * @return
     */
    public Result comprehensiveSortProblem(){
        List<ProblemAnswer> problemAnswers = new ArrayList<ProblemAnswer>();
        List<ProblemAnswer> comprehensiveSortProblemAnswers;
        List<Problem> problems=communityMapper.findAllProblem();
        if(problems.size()==0){
            return new Result(ResultEnum.NO_PROBLEMS_IN_DATABASE.getCode(),ResultEnum.NO_PROBLEMS_IN_DATABASE.getMsg(),null);
        }else{
            for(int i=0;i<problems.size();i++){
                List<Answer> answers=communityMapper.findAnswerByProblemId(problems.get(i).getId());
                problemAnswers.add(new ProblemAnswer(problems.get(i),answers));
            }
            comprehensiveSortProblemAnswers=comprehensiveSortAllProblem(problemAnswers);
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(),comprehensiveSortProblemAnswers);
        }
    }

    /**
     * 将所有问题按时间和问题的回答的点赞量的最大值综合排序输出
     * @param problemAnswers 数据库中查询的问题及回答列表
     * @return
     */
    public List<ProblemAnswer> comprehensiveSortAllProblem(List<ProblemAnswer> problemAnswers){
        ArrayList<SortProblem> sortProblems=new ArrayList<>();
        List<ProblemAnswer> comprehensiveSortProblemAnswers=new ArrayList<ProblemAnswer>();
        //整合问题及问题的flags值生成对应的SortProblem对象添加到列表中
        for (ProblemAnswer problemAnswer:problemAnswers) {
            int flags=0;
            int likes=0;//问题的回答的点赞量的最大值
            //提问时间距今的周数
            int timeDifference=(int)(Duration.between(
                    problemAnswer.getProblem().getTime().atStartOfDay(),
                    LocalDate.now().atStartOfDay())).toDays()/7;
            for(Answer answer:problemAnswer.getAnswers()){
                if(answer.getLikes()>likes) likes=answer.getLikes();
            }
            //按问题的回答的点赞量的最大值（加分项）和提问时间距今的周数（减分项）
            sortProblems.add(new SortProblem(problemAnswer,flags+likes-timeDifference));
        }
        //排序并返回结果，逆序返回即问题的flags值更大的在后面，
        // 这样返回到前端会再次逆序（默认的时间排序显示是直接将数据库中查询的所有问题逆序实现的，数据库提问时间越早越在前面）
        // 显示，就可以将问题的flags值更大的放在前面了
        Collections.sort(sortProblems);
        for(int i=sortProblems.size()-1;i>=0;i--){
            comprehensiveSortProblemAnswers.add(sortProblems.get(i).problemAnswer);
        }
        return comprehensiveSortProblemAnswers;
    }

    public Result searchProblem(String text){
        List<ProblemAnswer> problemAnswers = new ArrayList<ProblemAnswer>();
        List<Problem> problems=communityMapper.findProblemByContent(text);
        if(problems.size()==0){
            return new Result(ResultEnum.FIND_PROBLEM_FAILE.getCode(),ResultEnum.FIND_PROBLEM_FAILE.getMsg(),problemAnswers);
        }else{
            for(int i=0;i<problems.size();i++){
                List<Answer> answers=communityMapper.findAnswerByProblemId(problems.get(i).getId());
                problemAnswers.add(new ProblemAnswer(problems.get(i),answers));
            }
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(),problemAnswers);
        }
    }

    public Result isliked(String userPhone,int answerId){
        Like like=communityMapper.findLike(userPhone,answerId);
        if(like==null){
            return new Result(ResultEnum.LIKE_NOT_EXIST.getCode(),ResultEnum.LIKE_NOT_EXIST.getMsg(),null);
        }else{
            return new Result(ResultEnum.LIKE_EXIST.getCode(),ResultEnum.LIKE_EXIST.getMsg(),null);
        }
    }

    public Result changeToLiked(String userPhone,int answerId){
        if(communityMapper.addLike(userPhone, answerId)==1){
            return new Result(ResultEnum.ADD_LIKE_SUCCESS.getCode(),ResultEnum.ADD_LIKE_SUCCESS.getMsg(),null);
        }else{
            return new Result(ResultEnum.ADD_LIKE_FAILD.getCode(),ResultEnum.ADD_LIKE_FAILD.getMsg(),null);
        }
    }

    public Result changeToUnliked(String userPhone,int answerId){
        if(communityMapper.delLike(userPhone, answerId)==1){
            return new Result(ResultEnum.DEL_LIKE_SUCCESS.getCode(),ResultEnum.DEL_LIKE_SUCCESS.getMsg(),null);
        }else{
            return new Result(ResultEnum.DEL_LIKE_FAILD.getCode(),ResultEnum.DEL_LIKE_FAILD.getMsg(),null);
        }
    }

    public Result updateAnswerLikes(int answerId,int likes){
        if(communityMapper.updateAnswerLikes(answerId,likes)==1){
            return new Result(ResultEnum.UPDATE_ANSWER_LIKES_SUCCESS.getCode(),ResultEnum.UPDATE_ANSWER_LIKES_SUCCESS.getMsg(),null);
        }else{
            return new Result(ResultEnum.UPDATE_ANSWER_LIKES_FAILD.getCode(),ResultEnum.UPDATE_ANSWER_LIKES_FAILD.getMsg(),null);
        }
    }

    public Result findProblemById(int problemId){
        List<ProblemAnswer> problemAnswers=new ArrayList<>();
        Problem problem=communityMapper.findProblemById(problemId);
        if(problem==null){
            return new Result(ResultEnum.PROBLEM_NOT_EXIST.getCode(),ResultEnum.PROBLEM_NOT_EXIST.getMsg(),null);
        }else{
            List<Answer> answers=communityMapper.findAnswerByProblemId(problem.getId());
            problemAnswers.add(new ProblemAnswer(problem,answers));
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(),problemAnswers);
        }
    }

    public Result answerProblem(String content,LocalDate time,String userPhone,int problemId){
        if (communityMapper.addAnswer(content, time, userPhone, problemId)==1){
            return new Result(ResultEnum.ADD_ANSWER_SUCCESS.getCode(),ResultEnum.ADD_ANSWER_SUCCESS.getMsg(),null);
        }else{
            return new Result(ResultEnum.ADD_ANSWER_FAILD.getCode(),ResultEnum.ADD_ANSWER_FAILD.getMsg(),null);
        }
    }

    public Result updateAnswer(int answerId,String content,LocalDate time){
        if (communityMapper.updateAnswer(answerId, content, time)==1){
            return new Result(ResultEnum.UPDATE_ANSWER_SUCCESS.getCode(),ResultEnum.UPDATE_ANSWER_SUCCESS.getMsg(),null);
        }else{
            return new Result(ResultEnum.UPDATE_ANSWER_FAILD.getCode(),ResultEnum.UPDATE_ANSWER_FAILD.getMsg(),null);
        }
    }

}
