package com.wuwii.suit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by KronChan on 2018/4/25 17:51.
 */
@RunWith(Suite.class) // 1. 更改测试运行方式为 Suite
// 2. 将测试类传入进来
@Suite.SuiteClasses({TaskOneTest.class, TaskTwoTest.class, TaskThreeTest.class})
public class SuitTest {
    /**
     * 测试套件的入口类只是组织测试类一起进行测试，无任何测试方法，
     */
}
