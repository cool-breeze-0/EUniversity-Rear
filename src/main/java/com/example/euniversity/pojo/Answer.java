package com.example.euniversity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private int id;
    private String content;
    private Timestamp time;
    private int likes;
    private String userPhone;
    private int problemId;
}
