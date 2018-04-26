package com.wuwii.dao;

import com.wuwii.entity.Employee;
import com.wuwii.entity.EmployeeDetail;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertThat;

/**
 * Spring Data JPA 测试
 * Created by KronChan on 2018/4/26 8:30.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    @Transactional
    public void testSave() {
        Employee employee = new Employee();
        EmployeeDetail detail = new EmployeeDetail();
        detail.setName("kronchan");
        detail.setAge(24);
        employee.setDetail(detail);
        assertThat(detail.getName(), Matchers.is(employeeDao.save(employee).getDetail().getName()));
        ;
    }
}