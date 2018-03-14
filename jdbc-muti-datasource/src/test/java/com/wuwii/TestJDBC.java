package com.wuwii;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/14 18:29</pre>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJDBC {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate jdbcTemplate1;

    @Before
    public void before() {
        jdbcTemplate.execute("DELETE FORM employee");
        jdbcTemplate1.execute("DELETE FORM employee");
    }

    @Test
    public void testJDBC() {
        jdbcTemplate.update("insert into employee(id,name,age) VALUES (1, 'wuwii', 24)");
        jdbcTemplate1.update("insert into employee(id,name,age) VALUES (1, 'kronchan', 23)");
        Assert.assertThat("wuwii", Matchers.equalTo(jdbcTemplate.queryForObject("select name form employee where id=1", String.class)));
        Assert.assertThat("kronchan", Matchers.equalTo(jdbcTemplate1.queryForObject("select name form employee where id=1", String.class)));

    }
}
