package com.wuwii.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       // Authentication authentication = filter.attemptAuthentication(request, response);
        return null;
    }

}
