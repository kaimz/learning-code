package com.wuwii.repository;

import com.wuwii.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by KronChan on 2018/4/26 12:12.
 */
public interface IUserRepository extends JpaRepository<User, Long> {
    boolean updateUser(User user);
}
