package com.wuwii.service1.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wuwii.entity.Employee;
import com.wuwii.entity.QEmployee;
import com.wuwii.service1.EmployeeService1;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/26 18:39</pre>
 */
@Service
public class EmployeeServiceImpl1 implements EmployeeService1 {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    @Override
    public void save(Employee employee) {
        var qEmployee = QEmployee.employee;
        jpaQueryFactory.update(qEmployee).set(qEmployee, employee).execute();
    }

    @Override
    public void update(Employee employee) {
        var qEmployee = QEmployee.employee;

    }

    @Override
    public void findOne(Long id) {

    }

    @Override
    public void page(Employee employee, Pageable pageable) {

    }


}
