package com.wuwii.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Date;

/**
 * @author KronChan
 * @version 1.0
 * @since <pre>2018/4/18 22:45</pre>
 */
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtUtil {
    /**
     * 密钥
     */
    private String secret;
    /**
     * 有效期限
     */
    private int expire;
    /**
     * 存储 token
     */
    private String header;

    /**
     * 生成jwt token
     *
     * @param username
     * @return token
     */
    public String generateToken(String username) {
        Date nowDate = new Date();

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                // 后续获取 subject 是 username
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(DateUtils.addDays(nowDate, expire))
                // 这里我采用的是 HS512 算法
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 解析 token，
     * 利用 jjwt 提供的parser传入秘钥，
     *
     * @param token token
     * @return 数据声明 Map<String, Object>
     */
    private Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public String getUsernameFromToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new BadCredentialsException("无效 token");
        }
        Claims claims = getClaimByToken(token);
        if (claims == null || isTokenExpired(claims.getExpiration())) {
            throw new BadCredentialsException(header + "失效，请重新登录");
        }
        return claims.getSubject();
    }

}
