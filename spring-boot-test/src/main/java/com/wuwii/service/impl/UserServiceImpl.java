package com.wuwii.service.impl;

import com.wuwii.model.User;
import com.wuwii.repository.IUserRepository;
import com.wuwii.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by KronChan on 2018/4/26 12:14.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public User findOne(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public boolean updateUsername(Long id, String username) {
        assert username != null;
        User user = findOne(id);
        if (user == null) {
            return false;
        }
        user.setUsername(username);
        return username.equals(userRepository.save(user).getUsername());
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
