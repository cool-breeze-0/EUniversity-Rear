package com.example.euniversity.dao;

import com.example.euniversity.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int addUser(User user);
    User findUserByPhone(String phone);
    int updatePassword(@Param("phone") String phone, @Param("password") String password);
    int updateProfileInformation(@Param("phone") String phone,
                                 @Param("nikeName") String nikeName,
                                 @Param("gender") int gender,
                                 @Param("province") String province,
                                 @Param("city") String city,
                                 @Param("identity") String identity);
}
