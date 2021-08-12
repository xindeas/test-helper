package com.testhelper.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.testhelper.generator"})
public class TestHelperAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestHelperAdminApplication.class, args);
    }

}
