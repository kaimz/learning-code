package com.wuwii.config.security;

import com.wuwii.dao.UserDao;
import com.wuwii.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * 接受一个String类型的用户名参数，返回 UserDetails对象。
 */
@Slf4j
@CacheConfig(cacheNames = "users")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Cacheable(key = "#p0")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username is not valid.");
        }
        log.debug("The User is {}", user);
        return SecurityModelFactory.create(user);
    }
}
