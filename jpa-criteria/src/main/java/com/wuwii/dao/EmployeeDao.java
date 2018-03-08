package com.wuwii.dao;

import com.wuwii.entity.Employee;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/8 10:03</pre>
 */
@Repository
public interface EmployeeDao extends JpaSpecificationExecutor<Employee>, PagingAndSortingRepository<Employee, Id> {
}
