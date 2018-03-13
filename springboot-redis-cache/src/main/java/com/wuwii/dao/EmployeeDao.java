package com.wuwii.dao;

import com.wuwii.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/13 10:38</pre>
 */
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
}
