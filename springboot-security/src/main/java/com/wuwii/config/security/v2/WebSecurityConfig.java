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
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
//jsr250Enabled有三种注解，分别是@RolesAllowed,@PermitAll,@DenyAll,功能跟名字一样，
// securedEnabled 开启注解
// prePostEnabled  类似用的最多的是 @PreAuthorize
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }


    @Bean
    public UserDetailsService customService() {
        return new UserDetailServiceImpl();
    }

    /**
     * 认证
     */
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

    /**
     * BCrypt算法免除存储salt
     * BCrypt算法将salt随机并混入最终加密后的密码，验证时也无需单独提供之前的salt，从而无需单独处理salt问题。
     *
     * @return
     */
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

    /**
     * @param http
     * @throws Exception
     */
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

    /**
     * Web层面的配置，一般用来配置无需安全检查的路径
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
                        "**.js",
                        "**.css",
                        "/images/**",
                        "/webjars/**",
                        "/**/favicon.ico"
                );
    }
}
