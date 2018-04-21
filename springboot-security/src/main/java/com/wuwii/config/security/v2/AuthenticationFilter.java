package com.wuwii.config.security.v2;

import com.wuwii.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author KronChan
 * @version 2.0
 * @since <pre>2018/4/21 22:48</pre>
 */
@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private JwtUtil jwtUtil;

    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (isLoginRequest(httpRequest, httpResponse)) {
            processLogin(httpRequest, httpResponse);
        }
        String token = obtainToken(httpRequest);
        if (StringUtils.isNotBlank(token)) {

        }
    }
    /**
     * 登陆成功调用，返回 token
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        String token = jwtUtil.generateToken(authResult.getName());
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().print(token);
    }

    private boolean isLoginRequest(HttpServletRequest request, HttpServletResponse response) {
        return requiresAuthentication(request, response) && "POST".equalsIgnoreCase(request.getMethod());
    }

    private String obtainToken(HttpServletRequest request) {
        return request.getHeader(jwtUtil.getHeader());
    }

    private void processLogin(HttpServletRequest request, HttpServletResponse response) {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        Authentication authentication = tryAuthenticationWithUsernameAndPassword(username, password);
    }

    private void processTokenAuthentication(String token) {
        Authentication resultOfAuthentication = tryToAuthenticateWithToken(token);
        // 设置上下文用户信息以及权限
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
    }

    private Authentication tryAuthenticationWithUsernameAndPassword(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        return tryToAuthenticate(authentication);
    }

    private Authentication tryToAuthenticateWithToken(String token) {
        PreAuthenticatedAuthenticationToken requestAuthentication = new PreAuthenticatedAuthenticationToken(token, null);
        return tryToAuthenticate(requestAuthentication);
    }

    private Authentication tryToAuthenticate(Authentication requestAuth) {
        Authentication responseAuth = authenticationManager.authenticate(requestAuth);
        if (responseAuth == null || !responseAuth.isAuthenticated()) {
            throw new InternalAuthenticationServiceException("Unable to authenticate Domain User for provided credentials");
        }
        log.debug("User successfully authenticated");
        return responseAuth;
    }
}
