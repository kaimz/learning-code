package com.wuwii.service;

import com.wuwii.entity.Employee;
import com.wuwii.form.EmployeeSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/8 10:04</pre>
 */
public interface EmployeeService {
    /**
     * 分页查询
     *
     * @param search   查询属性
     * @param pageable 分页和排序
     * @return 分页数据
     */
    Page<Employee> pageBySearch(EmployeeSearch search, Pageable pageable);

    void save(Employee em);

    /**
     * Search age gt or eq the parameter
     *
     * @param age
     * @return
     */
    List<Employee> listByAge(Integer age);

}
