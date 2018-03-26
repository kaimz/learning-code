package com.wuwii.service1;

import com.wuwii.entity.Employee;
import org.springframework.data.domain.Pageable;

/**
 * 使用 JPAQueryFactory 实现复杂 CURD
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/26 18:38</pre>
 */
public interface EmployeeService1 {
    void save(Employee employee);

    void update(Employee employee);

    void findOne(Long id);

    void page(Employee employee, Pageable pageable);
}
