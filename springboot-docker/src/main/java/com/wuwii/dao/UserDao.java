package com.wuwii.dao;

import com.wuwii.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
}
