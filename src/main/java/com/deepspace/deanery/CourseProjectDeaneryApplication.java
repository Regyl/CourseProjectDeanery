package com.deepspace.deanery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class})
public class CourseProjectDeaneryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseProjectDeaneryApplication.class, args);
    }

}
