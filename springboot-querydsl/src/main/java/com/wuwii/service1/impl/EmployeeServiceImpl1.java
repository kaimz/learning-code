package com.wuwii.service1.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.wuwii.entity.Employee;
import com.wuwii.entity.EmployeeDetail;
import com.wuwii.entity.Job;
import com.wuwii.entity.QEmployee;
import com.wuwii.service1.EmployeeService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public long save(Employee employee) {
        QEmployee qEmployee = QEmployee.employee;
        return jpaQueryFactory.update(qEmployee).set(qEmployee, employee).execute();
    }

    @Override
    public long update(Employee employee) {
        QEmployee qEmployee = QEmployee.employee;
        JPAUpdateClause jpaUpdateClause = jpaQueryFactory.update(qEmployee);
        Optional<Employee> optional = Optional.of(employee);
        optional.map(Employee::getDetail).ifPresent(d -> {
            jpaUpdateClause.set(qEmployee.detail, employee.getDetail());
        });
        optional.map(Employee::getJob).ifPresent(j -> {
            jpaUpdateClause.set(qEmployee.job, employee.getJob());
        });
        return jpaUpdateClause.where(qEmployee.id.eq(employee.getId())).execute();
    }

    @Override
    public Employee findOne(Long id) {
        QEmployee qEmployee = QEmployee.employee;
        return jpaQueryFactory.selectFrom(qEmployee).where(qEmployee.id.eq(id)).fetchOne();
    }

    @Override
    public QueryResults<Employee> page(Employee employee, Pageable pageable) {
        QEmployee qEmployee = QEmployee.employee;
        JPAQuery jpaQuery = jpaQueryFactory.selectFrom(qEmployee);
        Optional<Employee> optional = Optional.of(employee);
        // search by phone if it is not null
        optional.map(Employee::getDetail).map(EmployeeDetail::getPhone).ifPresent(p -> {
            jpaQuery.leftJoin(qEmployee.detail).on(qEmployee.detail.phone.like("%" + p));
        });
        optional.map(Employee::getJob).map(Job::getName).ifPresent(j -> {
            jpaQuery.leftJoin(qEmployee.job).on(qEmployee.job.name.eq(j));
        });
        return jpaQuery.fetchResults();
    }


}
