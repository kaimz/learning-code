package com.wuwii.service;

import com.wuwii.entity.Employee;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/13 10:40</pre>
 */
public interface EmployeeService {
    Employee findOne(Long id);

    Employee update(Employee employee);

    void delete(Long id);
}
