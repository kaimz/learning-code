package com.wuwii.config.security;

import com.wuwii.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * https://blog.csdn.net/itguangit/article/details/78960127
 * auth 拦截器
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/4/18 23:20</pre>
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 过滤，我目前使用的是默认的，可以自己看源码按需求更改
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // todo 可以做些其他的验证操作，比如验证码
        super.doFilter(request, response, chain);
    }

    /**
     * 登陆成功调用
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        String token = jwtUtil.generateToken(authResult.getName());
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().print(token);
    }
}
