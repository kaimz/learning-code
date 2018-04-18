package com.wuwii.password;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 */

public class BCryptPasswordEncoderTest {

    /**
     * 获取密码
     */
    @Test
    public void testEquals() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(5);
        String encodePassword = encoder.encode("123456");
        System.out.println(encodePassword);
    }
}
