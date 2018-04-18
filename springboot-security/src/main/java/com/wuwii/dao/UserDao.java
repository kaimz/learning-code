package com.wuwii.dao;

import com.wuwii.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
