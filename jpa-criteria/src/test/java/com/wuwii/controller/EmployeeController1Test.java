package com.wuwii.controller;

import com.wuwii.form.EmployeeResult;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
        // 由于我返回的是 List 类型的，一直想不到办法解决，网上给出了解决办法，使用 exchange 函数代替
        //public <T> ResponseEntity<T> exchange(String url, HttpMethod method,
        //			HttpEntity<?> requestEntity, ParameterizedTypeReference<T> responseType,
        //			Object... urlVariables) throws RestClientException {
        ParameterizedTypeReference<List<EmployeeResult>> type = new ParameterizedTypeReference<List<EmployeeResult>>() {};
        ResponseEntity<List<EmployeeResult>> result = restTemplate.exchange("/emp", HttpMethod.GET, null, type);
        Assert.assertThat(result.getBody().get(0).getName(), Matchers.notNullValue());
    }
}
