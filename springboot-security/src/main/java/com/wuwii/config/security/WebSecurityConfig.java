package com.wuwii.config.security;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * http://nealma.com/2016/04/30/spring-boot-4-security/
 * https://juejin.im/entry/5990130cf265da3e213f0f81
 *
 * http://www.spring4all.com/article/420
 * https://juejin.im/post/59d5bbebf265da066c233d0e
 * @author kronchan
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//jsr250有三个注解，分别是@RolesAllowed,@PermitAll,@DenyAll,功能跟名字一样，
// securedEnabled 开启注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    /**
     * 注入 loginfilter 时候需要，注入 authenticationManager
     */
    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationManager(authenticationManager());
        return loginFilter;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }
    @Bean
    public UserDetailsService customService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    /**
     * BCrypt算法免除存储salt
     * BCrypt算法将salt随机并混入最终加密后的密码，验证时也无需单独提供之前的salt，从而无需单独处理salt问题。
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }
    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                // 注入身份的 Bean
                .authenticationProvider(authenticationProvider())
                .userDetailsService(userDetailsService())
                // 默认登陆的加密，自定义登陆的时候无效
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 开发的时候关闭 csrf 不然不好调试
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
                //.failureForwardUrl("/login.html")
                .permitAll()
                .and()
                // 登出
                .logout()
                // 注销的时候删除会话
                .deleteCookies("JSESSIONID")
                // 默认登出请求为 /logout，可以用下面自定义
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // 自定义登出成功的页面，默认为登陆页
                .logoutSuccessUrl("/logout.html")
                .permitAll()
                .and()
                // 开启 cookie 保存用户信息
                .rememberMe()
                // cookie 有效时间
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                // 设置cookie 的私钥，默认为随机生成的key
                .key("remember")
                .and()
                //验证登陆
                .addFilter(loginFilter())
                //验证token
                .addFilter(jwtAuthenticationFilter());
    }

    /**
     * Web层面的配置，一般用来配置无需安全检查的路径
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
                        "/**/favicon.ico",
                        "/swagger-ui.html",
                        "/swagger-resources/**"
                );
    }
}
