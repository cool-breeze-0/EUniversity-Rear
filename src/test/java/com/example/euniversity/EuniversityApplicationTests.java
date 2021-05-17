package com.example.euniversity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.time.LocalDate;

@SpringBootTest
class EuniversityApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println("\"综合\"");
    }

}
