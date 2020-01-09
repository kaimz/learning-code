package com.wuwii.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java 正则 api 基础训练
 *
 * @author kai.zhang
 * @date 2020/1/9 21:21
 */
public class Regular01 {
    /**
     * 生成一个正则表达式的模式,注意只能用静态方法生成,
     */
    private static final Pattern HTTP_PATTERN = Pattern.compile("^https?://.*");

    private static void matchHttp(String http) {
        // 创建指定字符串的匹配对象
        Matcher matcher = HTTP_PATTERN.matcher(http);
        // 根据创建好的匹配对象,我们可以根据正则表达式的规则操作字符串
        // matches 表示,正则能匹配整个字符串,则返回 true,否则返回 false
        boolean matches = matcher.matches();
        if (matches) {
            System.out.printf("http:%s, matches result=true", http);
        } else {
            System.err.printf("http:%s, matches result=false", http);
        }
    }

    public static void main(String[] args) {
        String http = "https://github.com/kaimz";
        matchHttp(http);
    }
}
