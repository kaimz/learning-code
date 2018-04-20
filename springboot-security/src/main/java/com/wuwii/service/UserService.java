package com.wuwii.service;

import com.wuwii.entity.User;
import com.wuwii.vo.UserAddDTO;
import com.wuwii.vo.UserVO;

import java.util.List;

/**
 *
 */
public interface UserService {
    UserVO castUserVO(User user);

    List<UserVO> castUserVO(List<User> users);

    List<User> findAll();

    void insertUser(User user);

    void insertUser(UserAddDTO userAddDTO);
}
