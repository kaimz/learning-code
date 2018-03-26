package com.wuwii.service1;

import com.querydsl.core.QueryResults;
import com.wuwii.entity.Employee;
import org.springframework.data.domain.Pageable;

/**
 * 使用 JPAQueryFactory 实现复杂 CURD
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/26 18:38</pre>
 */
public interface EmployeeService1 {
    long save(Employee employee);

    long update(Employee employee);

    Employee findOne(Long id);

    QueryResults<Employee> page(Employee employee, Pageable pageable);
}
