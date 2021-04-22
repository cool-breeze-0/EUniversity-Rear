package com.example.euniversity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class University {
    private int id;
    private String name;
    private String type;
    private String level;
    private String educationLevel;
    private String nature;
    private String area;
    private String introduction;
}
