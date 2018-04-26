package com.wuwii.form;

import com.wuwii.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/8 19:24</pre>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResult extends Employee {
    private String name;
    private Integer age;
}
