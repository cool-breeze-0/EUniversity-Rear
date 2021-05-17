package com.example.euniversity.dao;

import com.example.euniversity.pojo.University;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UniversityMapper {
    List<University> findUniversity(
            @Param("start") int start
    );
    List<University> searchUniversity(
            @Param("text") String text,
            @Param("start") int start
    );
    List<University> filterUniversity(@Param("universityTypeString") String universityTypeString,
                                      @Param("universityLevelString") String universityLevelString,
                                      @Param("universityNatureString") String universityNatureString,
                                      @Param("provinceString") String provinceString,
                                      @Param("f985String") String f985String,
                                      @Param("f211String") String f211String,
                                      @Param("dualClassString") String dualClassString,
                                      @Param("start") int start
    );
    University findUniversityById(int universityId);
    String findUniversityIntroductionById(int universityId);
    String findUniversityScore(
            @Param("universityId") int universityId,
            @Param("province") String province,
            @Param("division") String division,
            @Param("year") int year
    );
    Integer findUniversityIdByName(String universityName);
}
