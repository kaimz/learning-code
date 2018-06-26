package com.wuwii.observer;

/**
 * Created by KronChan on 18/6/26 上午10:19.
 */
public class CouponOberver implements Observer {
    @Override
    public void receive(String product) {
        System.out.println(String.format("Give you some coupons for %s.", product));
    }
}
