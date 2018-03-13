package com.wuwii;

import com.wuwii.entity.Employee;
import com.wuwii.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/13 10:45</pre>
 */
@RestController
public class controller {
    @Autowired
    private EmployeeService service;

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity update(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("删除成功");
    }
}
