package com.wuwii.config.security.v1;

/**
 * http://nealma.com/2016/04/30/spring-boot-4-security/
 * https://juejin.im/entry/5990130cf265da3e213f0f81
 *
 * http://www.spring4all.com/article/420
 * https://juejin.im/post/59d5bbebf265da066c233d0e
 * @author kronchan
 * @version 1.0
 */
//@Configuration
//@EnableWebSecurity // 开启 Security
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
////jsr250Enabled有三种注解，分别是@RolesAllowed,@PermitAll,@DenyAll,功能跟名字一样，
//// securedEnabled 开启注解
//// prePostEnabled  类似用的最多的是 @PreAuthorize
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Bean
//    public JwtUtil jwtUtil() {
//        return new JwtUtil();
//    }
//
//    /**
//     * 注入 LoginFilter 时候需要，注入 authenticationManager
//     */
//    @Bean
//    public LoginFilter loginFilter() throws Exception {
//        LoginFilter loginFilter = new LoginFilter();
//        loginFilter.setAuthenticationManager(authenticationManager());
//        return loginFilter;
//    }
//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
//        return new JwtAuthenticationFilter(authenticationManager());
//    }
//    @Bean
//    public UserDetailsService customService() {
//        return new UserDetailServiceImpl();
//    }
//
//    /**
//     * 认证
//     */
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        return new CustomAuthenticationProvider();
//    }
//
//    /**
//     * BCrypt算法免除存储salt
//     * BCrypt算法将salt随机并混入最终加密后的密码，验证时也无需单独提供之前的salt，从而无需单独处理salt问题。
//     * @return
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(5);
//    }
//
//    /**
//     * 主要是对身份验证的设置
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth
//                // 注入身份的 Bean
//                .authenticationProvider(authenticationProvider())
//                .userDetailsService(userDetailsService())
//                // 默认登陆的加密，自定义登陆的时候无效
//                .passwordEncoder(passwordEncoder());
//        // 在内存中设置固定的账户密码以及身份信息
//        /*auth
//                .inMemoryAuthentication().withUser("user").password("password").roles("USER").and()
//                .withUser("admin").password("password").roles("USER", "ADMIN");*/
//    }
//
//    /**
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                // 关闭 csrf
//                .csrf().disable()
//                // 设置 session 状态 STATELESS 无状态
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                // 需要权限验证
//                .mvcMatchers("/user/**").authenticated()
//                .and()
//                // 登陆页面
//                .formLogin()
//                //.loginPage("/login.html")
//                // 登陆成功跳转页面
//                .defaultSuccessUrl("/")
//                //.failureForwardUrl("/login.html")
//                .permitAll()
//                .and()
//                // 登出
//                //.logout()
//                // 注销的时候删除会话
//                //.deleteCookies("JSESSIONID")
//                // 默认登出请求为 /logout，可以用下面自定义
//                //.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                // 自定义登出成功的页面，默认为登陆页
//                //.logoutSuccessUrl("/logout.html")
//                //.permitAll()
//                //.and()
//                // 开启 cookie 保存用户信息
//                //.rememberMe()
//                // cookie 有效时间
//                //.tokenValiditySeconds(60 * 60 * 24 * 7)
//                // 设置cookie 的私钥，默认为随机生成的key
//                //.key("remember")
//                //.and()
//                //验证登陆的 filter
//                .addFilter(loginFilter())
//                //验证token的 filter
//                .addFilter(jwtAuthenticationFilter());
//    }
//
//    /**
//     * Web层面的配置，一般用来配置无需安全检查的路径
//     * @param web
//     * @throws Exception
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers(
//                        "**.js",
//                        "**.css",
//                        "/images/**",
//                        "/webjars/**",
//                        "/**/favicon.ico"
//                );
//    }
//}
