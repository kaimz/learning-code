package com.wuwii.service.impl;

import com.wuwii.entity.Employee;
import com.wuwii.entity.EmployeeDetail;
import com.wuwii.form.EmployeeSearch;
import com.wuwii.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * EmployeeServiceImpl Tester.
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>03/08/2018</pre>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {
    @Autowired
    private EmployeeService employeeService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: pageBySearch(EmployeeSearch search, Pageable pageable)
     */
    @Test
    public void testPageBySearch() throws Exception {
        EmployeeSearch employeeSearch = new EmployeeSearch(null, null, "程序猿");
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Page<Employee> employees = employeeService.pageBySearch(employeeSearch, new PageRequest(0, 5, sort));
        Assert.assertThat("18772383543", Matchers.equalTo(employees.getContent().get(0).getDetail().getPhone()));
    }

    @Test
    public void testSave() {
        Employee employee = new Employee();
        EmployeeDetail detail = new EmployeeDetail();
        detail.setAge(22);
        detail.setName("jams");
        detail.setPhone("42345");
        employee.setDetail(detail);
        employeeService.save(employee);
    }

    @Test
    public void testListByAge() {
        List<Employee> list = employeeService.listByAge(24);
        Assert.assertThat(1L, Matchers.equalTo(list.get(0).getId()));
    }


} 
