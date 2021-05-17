package com.example.euniversity.controller;

import com.example.euniversity.service.UniversityService;
import com.example.euniversity.util.Result;
import com.example.euniversity.util.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping(value = "/findUniversity",produces = "application/json;charset=UTF-8")
    public Result findUniversity(int time){
        return universityService.findUniversity(time);
    }

    @GetMapping(value = "/searchUniversity",produces = "application/json;charset=UTF-8")
    public Result searchUniversity(String text,int time){
        if(text.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return universityService.searchUniversity(text,time);
        }
    }

    @PostMapping(value = "/filterUniversity",produces = "application/json;charset=UTF-8")
    public Result filterUniversity(String universityTypeString, String universityLevelString,
                                   String educationLevelString, String universityNatureString, String provinceString,int time){
        if(universityTypeString.equals("")||universityLevelString.equals("")||educationLevelString.equals("")||
                universityNatureString.equals("")||provinceString.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return universityService.filterUniversity(universityTypeString,universityLevelString,
                    educationLevelString,universityNatureString,provinceString,time);
        }
    }

    @GetMapping(value = "/findUniversityById",produces = "application/json;charset=UTF-8")
    public Result findUniversityById(int universityId){
        return universityService.findUniversityById(universityId);
    }

    @GetMapping(value = "/findUniversityIntroductionById",produces = "application/json;charset=UTF-8")
    public Result findUniversityIntroductionById(int universityId){
        return universityService.findUniversityIntroductionById(universityId);
    }

    @GetMapping(value = "/findUniversityScore",produces = "application/json;charset=UTF-8")
    public Result findUniversityScore(int universityId,String province,String division){
        if(province.equals("")||division.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return universityService.findUniversityScore(universityId,province,division);
        }
    }

    @GetMapping(value = "/findUniversityIdByName",produces = "application/json;charset=UTF-8")
    public Result findUniversityIdByName(String universityName){
        if(universityName.equals("")){
            return new Result(ResultEnum.INPUT_IS_NULL.getCode(),ResultEnum.INPUT_IS_NULL.getMsg(),null);
        }else{
            return universityService.findUniversityIdByName(universityName);
        }
    }

}
