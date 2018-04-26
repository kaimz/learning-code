package com.wuwii.service;

import com.wuwii.model.User;

/**
 * Created by KronChan on 2018/4/26 12:13.
 */
public interface IUserService {

    User findOne(Long id);

    boolean updateUsername(Long id, String usernmae);
}
