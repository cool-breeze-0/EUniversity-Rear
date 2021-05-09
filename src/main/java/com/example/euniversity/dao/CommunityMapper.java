package com.example.euniversity.dao;

import com.example.euniversity.pojo.Answer;
import com.example.euniversity.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Mapper
@Repository
public interface CommunityMapper {
    List<Problem> findAllProblem();
    List<Answer> findAnswerByProblemId(int problemId);
    int addProblem(@Param("content") String content,@Param("time") LocalDate time,@Param("userPhone") String userPhone);
}
