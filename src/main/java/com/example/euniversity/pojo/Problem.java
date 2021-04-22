package com.example.euniversity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Problem {
    private int id;
    private String content;
    private Timestamp time;
    private String userPhone;
}
