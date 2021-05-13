package com.example.euniversity.dao;

import com.example.euniversity.pojo.Answer;
import com.example.euniversity.pojo.Like;
import com.example.euniversity.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Mapper
@Repository
public interface CommunityMapper {
    List<Problem> findAllProblem();
    List<Answer> findAnswerByProblemId(int problemId);

    int addProblem(@Param("content") String content,@Param("time") LocalDate time,@Param("userPhone") String userPhone);

    List<Problem> findUserProblem(String userPhone);

    List<Answer> findAnswerByUserPhone(String userPhone);

    Problem findProblemById(int problemId);

    List<Problem> findProblemByContent(String text);

    Like findLike(@Param("userPhone")String userPhone,@Param("answerId") int answerId);

    int addLike(@Param("userPhone")String userPhone,@Param("answerId") int answerId);

    int delLike(@Param("userPhone")String userPhone,@Param("answerId") int answerId);

    int updateAnswerLikes(@Param("answerId")int answerId,@Param("likes")int likes);

    int addAnswer(
            @Param("content") String content,
            @Param("time") LocalDate time,
            @Param("userPhone") String userPhone,
            @Param("problemId") int problemId
    );

    int updateAnswer(
            @Param("answerId") int answerId,
            @Param("content") String content,
            @Param("time") LocalDate time
    );
}
