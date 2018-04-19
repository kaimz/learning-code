package com.wuwii.config.security;

import com.wuwii.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
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
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String username = obtainUsername(httpRequest);
        Authentication authResult;
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
               authResult = super.attemptAuthentication(httpRequest, httpResponse);
               if (authResult == null) {
                   // return immediately as subclass has indicated that it hasn't completed
                   // authentication
                   return;
               }
               String token = jwtUtil.generateToken(username);
               onSuccess(httpResponse, token);
               return;
        }
        // 没用用户名，则使用 token
        String token = obtainToken(httpRequest);
        // todo 检查 token
        username = jwtUtil.getUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
        // 将结果放入安全上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 获取 token
     * @param httpRequest
     * @return
     */
    private String obtainToken(HttpServletRequest httpRequest) {
        String key = jwtUtil.getHeader();
        //从header中获取token
        String token = httpRequest.getHeader(key);
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            return httpRequest.getParameter(key);
        }
        if (StringUtils.isBlank(token)) {
            // 从 cookie 获取 token
            Cookie[] cookies = httpRequest.getCookies();
            if (null == cookies || cookies.length == 0) {
                return null;
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }


    /**
     *  返回给客户端 token
     * @throws IOException
     */
    private void onSuccess(HttpServletResponse response, String token) throws IOException {
        ((HttpServletResponse) response).setStatus(HttpStatus.OK.value());
        ((HttpServletResponse) response).getWriter().print(token);
    }

    /**
     * 登陆成功调用
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        String token = jwtUtil.generateToken(authResult.getName());
        onSuccess(response, token);
    }
}
