package com.example.euniversity.service;

import com.example.euniversity.dao.UniversityMapper;
import com.example.euniversity.pojo.University;
import com.example.euniversity.util.Result;
import com.example.euniversity.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UniversityService {
    @Autowired
    private UniversityMapper universityMapper;

    public Result findUniversity(int time){
        List<University> universityList=universityMapper.findUniversity((time-1)*20);
        if(universityList.size()==0){
            return new Result(ResultEnum.NO_UNIVERSITYS_IN_DATABASE.getCode(),ResultEnum.NO_UNIVERSITYS_IN_DATABASE.getMsg(),null);
        }else{
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(),universityList);
        }
    }

    public Result searchUniversity(String text,int time){
        List<University> universityList=universityMapper.searchUniversity(text,(time-1)*20);
        if(universityList.size()==0){
            return new Result(ResultEnum.FIND_UNIVERSITY_FAILE.getCode(),ResultEnum.FIND_UNIVERSITY_FAILE.getMsg(),null);
        }else{
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(),universityList);
        }
    }

    public Result filterUniversity(String universityTypeString, String universityLevelString,
                                   String educationLevelString, String universityNatureString, String provinceString,int time){
        //根据安卓端传来的学历层次字符串生成不同的f985、f211、dualclass筛选列表
        String f985String="";
        String f211String="";
        String dualClassString="";
        if(educationLevelString.equals("不限")){
            f985String="1,2";
            f211String="1,2";
            dualClassString="\"双一流\",\"\"";
        }else{
            String[] educationLevelStringList=educationLevelString.split(",");
            switch (educationLevelStringList[educationLevelStringList.length-1]){
                case "985":
                    f985String="1";
                    f211String="1,2";
                    dualClassString="\"双一流\",\"\"";
                    break;
                case "211":
                    f985String="1,2";
                    f211String="1";
                    dualClassString="\"双一流\",\"\"";
                    break;
                case "双一流":
                    f985String="1,2";
                    f211String="1,2";
                    dualClassString="\"双一流\"";
                    break;
            }
        }
        List<University> universityList=universityMapper.filterUniversity(universityTypeString,universityLevelString,
                universityNatureString,provinceString,f985String,f211String,dualClassString,(time-1)*20);
        if(universityList.size()==0){
            return new Result(ResultEnum.FILTER_UNIVERSITY_FAILE.getCode(),ResultEnum.FILTER_UNIVERSITY_FAILE.getMsg(),null);
        }else{
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(),universityList);
        }
    }

    public Result findUniversityById(int universityId){
        University university=universityMapper.findUniversityById(universityId);
        if(university==null){
            return new Result(ResultEnum.UNIVERSITY_NOT_EXIST.getCode(),ResultEnum.UNIVERSITY_NOT_EXIST.getMsg(),null);
        }else{
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(), Arrays.asList(university));
        }
    }

    public Result findUniversityIntroductionById(int universityId){
        String universityIntroduction=universityMapper.findUniversityIntroductionById(universityId);
        if(universityIntroduction==null){
            return new Result(ResultEnum.UNIVERSITY_NOT_EXIST.getCode(),ResultEnum.UNIVERSITY_NOT_EXIST.getMsg(),null);
        }else{
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(), Arrays.asList(universityIntroduction));
        }
    }

    public Result findUniversityScore(int universityId,String province,String division){
        ArrayList<String> grades=new ArrayList<>();
        String g2020=universityMapper.findUniversityScore(universityId,province,division,2020);
        String g2019=universityMapper.findUniversityScore(universityId,province,division,2019);
        String g2018=universityMapper.findUniversityScore(universityId,province,division,2018);
        grades.add(g2020==null?"—":g2020);
        grades.add(g2019==null?"—":g2019);
        grades.add(g2018==null?"—":g2018);
        return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(), grades);
    }

    public Result findUniversityIdByName(String universityName){
        Integer universityId=universityMapper.findUniversityIdByName(universityName);
        if(universityId==null){
            return new Result(ResultEnum.UNIVERSITY_NOT_EXIST.getCode(),ResultEnum.UNIVERSITY_NOT_EXIST.getMsg(),null);
        }else {
            return new Result(ResultEnum.FIND_SUCCESS.getCode(),ResultEnum.FIND_SUCCESS.getMsg(),Arrays.asList(universityId));
        }
    }
}
