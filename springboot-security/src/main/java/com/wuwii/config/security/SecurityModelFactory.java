package com.wuwii.config.security;

import com.wuwii.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 生成认证需要的 UserDetails
 * @author KronChan
 */
public class SecurityModelFactory {
    public static UserDetails create(User user) {
        return new UserDetailsImpl(user);
    }
}
