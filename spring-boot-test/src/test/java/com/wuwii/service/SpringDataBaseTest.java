package com.wuwii.service;

import com.wuwii.model.User;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author KronChan
 * @date 2019-07-14 23:39
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringDataBaseTest {

  @Autowired
  private IUserService userService;

  @Test
  public void testSave() {
    User user = mockUser();
    User saveUser = userService.save(user);
    Assert.assertThat(saveUser.getUsername(), Matchers.equalTo(user.getUsername()));
  }

  private User mockUser() {
    User user = new User();
    user.setUsername("jack");
    user.setPassword("32145");
    return user;
  }

}
