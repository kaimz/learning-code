package com.wuwii.service.impl;

import com.wuwii.dao.UserDao;
import com.wuwii.entity.User;
import com.wuwii.service.UserService;
import com.wuwii.vo.UserAddVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void insertUser(User user) {
        user.setCreateDate(new Date());
        userDao.save(user);
    }

    @Override
    public void insertUser(UserAddVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        insertUser(user);
    }
}
