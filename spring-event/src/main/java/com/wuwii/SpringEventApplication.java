package com.wuwii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author KronChan
 * @date 2019-07-14 22:48
 */
@EnableAsync
@SpringBootApplication
public class SpringEventApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringEventApplication.class, args);
  }
}
