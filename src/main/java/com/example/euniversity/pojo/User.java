package com.example.euniversity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String phone;
    private String password;
    private String nikeName;
    private int gender;//0表示男，1表示女
    private String province;
    private String city;
    private String identity;
}
