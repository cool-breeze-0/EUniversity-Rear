package com.example.euniversity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityScore {
    private int universityId;
    private String birthplace;
    private String division;
    private Date year;
    private int grade;
}
