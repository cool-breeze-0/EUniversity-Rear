package com.example.euniversity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private int id;
    private String content;
    private LocalDate time;
    private int likes;
    private String userPhone;
    private int problemId;
}
