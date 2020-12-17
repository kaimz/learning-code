package com.wuwii.regular;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * find 多次匹配字符串的一部分
 *
 * @author kai.zhang
 * @date 2020/1/10 14:46
 */
public class Regular03 {
    public static final String END_MESSAGE = "group:{0}, 结束位置: {1}";
    private static final Pattern PATTERN = Pattern.compile("([a-zA-Z]{2})(?<digit>\\d{2,3})");
    private static final String START_MESSAGE = "group:{0}, 开始位置: {1}";

    private static void find(String str) {
        Matcher matcher = PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        // 查找与该模式匹配的输入序列的下一个子序列,能匹配返回 true,没有返回 false
        while (matcher.find()) {
            System.out.println(MessageFormat.format(START_MESSAGE, 0, matcher.start()));
            //注: matcher.group(0) 是匹配到的子序列整个的值
            System.out.println(matcher.group());
            System.out.println(MessageFormat.format(END_MESSAGE, 0, matcher.end()));
            // 匹配开始的位置
            System.out.println(MessageFormat.format(START_MESSAGE, 1, matcher.start(1)));
            System.out.println(matcher.group(1));
            // 匹配结束的位置
            System.out.println(MessageFormat.format(END_MESSAGE, 1, matcher.end(1)));

            System.out.println(MessageFormat.format(START_MESSAGE, "digit", matcher.start("digit")));
            //group2 被重命名 digit,也可以使用group2
            System.out.println(matcher.group("digit"));
            System.out.println(MessageFormat.format(END_MESSAGE, "digit", matcher.end("digit")));

            // 替换group1位置的内容，group2保持不变，新的内容保存在新的字符串sb上
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase() + matcher.group(2) + "a");

            System.out.println("----------------");
        }
        System.out.println(sb.toString());
        // 最后一次匹配的内容加到sb中,因为不加这个方法，后面没有匹配的内容的将没有加上
        matcher.appendTail(sb);
        System.out.println(sb);
        // 整行匹配的结果
        System.out.println(matcher.matches());
    }

    public static void main(String[] args) {
        String text = "aa11-bb223-Cc33dd";
        find(text);
    }
}
