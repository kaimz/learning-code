package com.wuwii.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式的捕获组(capture group)
 *
 * @author kai.zhang
 * @date 2020/1/9 21:42
 */
public class Regular02 {

    /**
     * 从正则表达式左侧开始,没出现一个左括号"(" 记做一个分组,分组编号从 1 开始. 0 代表整个表达式
     * 命名捕获组: 每个以左括号开始的捕获组,都紧跟着 ?,然后才是正则表达式
     * 另外: "(?:正则表达式)", 这种表达方式表示忽略该组,不计算编号.
     */
    private static final Pattern HTTP_PATTERN = Pattern.compile("^https?://(?<domain>[\\w\\.]+(com)|(cn)|(net))/.*(?<param>\\?.*)");

    private static void capturing(String http) {
        Matcher matcher = HTTP_PATTERN.matcher(http);
        matcher.find();
        System.out.println(matcher.group(0));
        // 同样可以 matcher.group(1);
        String domain = matcher.group("domain");
        System.out.println(domain);
        // 同样可以 matcher.group(2);
        String param = matcher.group("param");
        System.out.println(param);
    }

    public static void main(String[] args) {
        String http = "https://github.com/kaimz?tab=repositories";
        capturing(http);
    }
}
