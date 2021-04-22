package com.example.euniversity.dao;

import com.example.euniversity.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int addUser(User user);
}
