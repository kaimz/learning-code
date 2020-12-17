package com.wuwii.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kai.zhang
 * @date 2020/1/10 18:19
 */
public class Regular04 {

    private static void or() {
        // 或者使用 inssy(?:t|a|v)
        // ?:pattern 表示匹配 pattern 单不需要获取结果,是一个非获取匹配,不需要存储供以后使用
        Pattern pattern = Pattern.compile("do(ing)|(es)"); //do(?:ing|es)
        Matcher matcher = pattern.matcher("does");
        System.out.println(matcher.matches());
    }

    private static void test() {
        Pattern pattern = Pattern.compile("(?<domain>\\w+\\.)+");
        Matcher matcher = pattern.matcher("blog.wuwii");
        System.out.println(matcher.matches());

    }

    public static void main(String[] args) {
        // or();
        test();
    }

}
