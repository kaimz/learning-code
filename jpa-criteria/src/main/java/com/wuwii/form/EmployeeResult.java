package com.wuwii.form;

import com.wuwii.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/8 19:24</pre>
 */
@Data
@AllArgsConstructor
public class EmployeeResult extends Employee {
    private String name;
    private Integer age;
}
