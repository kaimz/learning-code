package com.wuwii.service;

import com.wuwii.entity.User;
import com.wuwii.vo.UserAddVO;

import java.util.List;

/**
 *
 */
public interface UserService {
    List<User> findAll();

    void insertUser(User user);

    void insertUser(UserAddVO userVO);
}
