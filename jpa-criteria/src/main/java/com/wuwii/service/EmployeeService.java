package com.wuwii.service;

import com.wuwii.entity.Employee;
import com.wuwii.form.EmployeeResult;
import com.wuwii.form.EmployeeSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.Tuple;
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

    /**
     * 参数化表达式
     *
     * @param age
     * @return
     */
    List<Employee> listByAge1(Integer age);

    /**
     * 分组统计重名数量
     *
     * @param name
     * @return
     */
    List<Tuple> groupByName(String name);

    /**
     * 使用 构造函数 装载查询出来的数据
     *
     * @return
     */
    List<EmployeeResult> findEmployee();
}
