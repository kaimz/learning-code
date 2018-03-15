package com.wuwii.module.user.dao;

import com.wuwii.module.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/15 8:49</pre>
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
}
