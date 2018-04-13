package com.wuwii;

import com.wuwii.dao.UserDao;
import com.wuwii.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class SpringbootDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDockerApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World.";
    }

    @Autowired
    private UserDao userDao;

    @GetMapping("/user")
    public List<User> getAllUser() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return userDao.findAll(sort);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userDao.save(user);
    }
}
