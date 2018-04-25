package com.wuwii;

import com.wuwii.service.AliPay;
import com.wuwii.service.Pay;
import com.wuwii.service.WeChatPay;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by KronChan on 2018/4/25 10:33.
 */
@Configuration
@ComponentScan(basePackages = {"com.wuwii.service"})
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        Map<Class<? extends Pay>, Pay> payMethod = Pay.PAY_METHOD;
        Pay aliPay = payMethod.get(AliPay.class);
        aliPay.pay(12);
        Pay weChatPay = payMethod.get(WeChatPay.class);
        weChatPay.pay(22);
    }
}
