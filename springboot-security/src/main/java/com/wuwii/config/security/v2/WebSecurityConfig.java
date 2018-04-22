package com.wuwii.config.security.v2;

import com.wuwii.config.security.UserDetailServiceImpl;
import com.wuwii.config.security.v2.authenticate.LoginAuthenticationProvider;
import com.wuwii.config.security.v2.authenticate.TokenAuthenticateProvider;
import com.wuwii.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * http://nealma.com/2016/04/30/spring-boot-4-security/
 * https://juejin.im/entry/5990130cf265da3e213f0f81
 * <p>
 * http://www.spring4all.com/article/420
 * https://juejin.im/post/59d5bbebf265da066c233d0e
 *
 * @author kronchan
 * @version 2.0
 */
@Configuration
@EnableWebSecurity // 开启 Security
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public UserDetailsService customService() {
        return new UserDetailServiceImpl();
    }

    @Bean("loginAuthenticationProvider")
    public AuthenticationProvider loginAuthenticationProvider() {
        return new LoginAuthenticationProvider();
    }

    @Bean("tokenAuthenticationProvider")
    public AuthenticationProvider tokenAuthenticationProvider() {
        return new TokenAuthenticateProvider();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager());
        return authenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }
    /**
     * 主要是对身份验证的设置
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(loginAuthenticationProvider())
                .authenticationProvider(tokenAuthenticationProvider())
                .userDetailsService(userDetailsService());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭 csrf
                .csrf().disable()
                // 设置 session 状态 STATELESS 无状态
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 需要权限验证
                .mvcMatchers("/user/**").authenticated()
                .and()
                // 登陆页面
                .formLogin()
                //.loginPage("/login.html")
                // 登陆成功跳转页面
                .defaultSuccessUrl("/")
                .failureForwardUrl("/login.html")
                .permitAll()
                .and()
                .addFilter(authenticationFilter())
        ;
    }
}
