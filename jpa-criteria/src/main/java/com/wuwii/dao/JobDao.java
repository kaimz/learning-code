package com.wuwii.dao;

import com.wuwii.entity.Employee;
import com.wuwii.entity.Job;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/8 10:44</pre>
 */
@Repository
public interface JobDao extends JpaSpecificationExecutor<Job>, PagingAndSortingRepository<Employee, Id> {
}
