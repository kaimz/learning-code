package com.wuwii.dao;

import com.wuwii.entity.Employee;
import com.wuwii.entity.QEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * https://www.jianshu.com/p/69dcb1b85bbb
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/26 18:41</pre>
 */
public interface EmployeeDao extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<QEmployee> {

}
