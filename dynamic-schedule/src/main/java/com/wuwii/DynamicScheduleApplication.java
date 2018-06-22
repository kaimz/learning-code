package com.wuwii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DynamicScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicScheduleApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
