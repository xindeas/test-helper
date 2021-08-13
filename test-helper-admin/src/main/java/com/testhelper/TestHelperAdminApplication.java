package com.testhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TestHelperAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestHelperAdminApplication.class, args);
        System.out.println("项目启动成功");
    }

}
