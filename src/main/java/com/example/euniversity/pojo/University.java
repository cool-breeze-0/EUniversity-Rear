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
    private String nature;
    private String province;
    private String city;
    private String url;
    private String logo;
    private String introduction;
    private String image;
    private int f985;
    private int f211;
    private String dualClass;

    public University(int id, String name, String type, String province, String city, String logo, int f985, int f211,
                      String dualClass) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.province = province;
        this.city = city;
        this.logo = logo;
        this.f985 = f985;
        this.f211 = f211;
        this.dualClass = dualClass;
    }
    public University(int id, String name, String type,String level,String nature, String province, String city,
                      String url,String logo,String image, int f985, int f211, String dualClass) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level= level;
        this.nature= nature;
        this.province = province;
        this.city = city;
        this.url = url;
        this.logo = logo;
        this.image = image;
        this.f985 = f985;
        this.f211 = f211;
        this.dualClass = dualClass;
    }
}
