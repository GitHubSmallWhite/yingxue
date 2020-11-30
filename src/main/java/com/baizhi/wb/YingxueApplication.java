package com.baizhi.wb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@tk.mybatis.spring.annotation.MapperScan("com.baizhi.wb.dao")
@SpringBootApplication
@MapperScan("com.baizhi.wb.dao")
public class YingxueApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxueApplication.class, args);
    }

}
