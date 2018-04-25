package com.wuwii.controller;

import com.wuwii.form.EmployeeResult;
import com.wuwii.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by KronChan on 2018/4/25 15:43.
 */
@RestController
@RequestMapping(value = "/emp", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResult>> listAll() {
        return ResponseEntity.ok(employeeService.findEmployee());
    }
}
