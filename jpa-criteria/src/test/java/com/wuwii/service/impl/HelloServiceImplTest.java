package com.wuwii.service.impl;

import com.wuwii.service.HelloService;
import org.junit.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by KronChan on 2018/4/25 16:13.
 */
public class HelloServiceImplTest {

    @Before
    public void before() throws Exception {
        System.out.println("before");
    }

    @After
    public void after() throws Exception {
        System.out.println("after");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After Class");
    }

    @Test
    public void say() {
        HelloService helloService = new HelloServiceImpl();
        helloService.say();
    }

    @Test(timeout = 1000)
    public void testTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Complete");
    }

    @Test(expected = NullPointerException.class)
    public void testNullException() {
        throw new NullPointerException();
    }

    @Test
    @Ignore
    public void testIgnore() {
        System.out.println("Ignore ?");
        throw new NullPointerException();
    }
}