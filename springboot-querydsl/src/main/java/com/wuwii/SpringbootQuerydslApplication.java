package com.wuwii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringbootQuerydslApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootQuerydslApplication.class, args);
	}
}
