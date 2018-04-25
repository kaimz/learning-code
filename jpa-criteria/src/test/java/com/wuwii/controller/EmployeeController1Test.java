package com.wuwii.controller;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * http://fanlychie.github.io/post/spring-boot-testing.html
 * Created by KronChan on 2018/4/25 19:02.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeController1Test {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void listAll() {
        ResponseEntity<List> result = restTemplate.getForEntity("/emp", List.class);
        Assert.assertThat(result.getBody(), Matchers.notNullValue());
    }

}
