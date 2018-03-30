package com.wuwii.module.system;

import com.wuwii.module.system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/15 8:48</pre>
 */
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
}
