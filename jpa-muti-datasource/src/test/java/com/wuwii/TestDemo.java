package com.wuwii;

import com.wuwii.module.system.dao.EmployeeDao;
import com.wuwii.module.system.entity.Employee;
import com.wuwii.module.user.dao.UserDao;
import com.wuwii.module.user.entity.User;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/15 8:50</pre>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private UserDao userDao;

    @Before
    public void before() {
        employeeDao.deleteAll();
        userDao.deleteAll();
    }

    @Test
    public void test() {
        Employee employee = new Employee(null, "wuwii", 24);
        employeeDao.save(employee);
        User user = new User(null, "KronChan", 23);
        userDao.save(user);
        Assert.assertThat(employee, Matchers.equalTo(employeeDao.findOne(Example.of(employee))));
        Assert.assertThat(user, Matchers.equalTo(userDao.findOne(Example.of(user))));
    }
}
