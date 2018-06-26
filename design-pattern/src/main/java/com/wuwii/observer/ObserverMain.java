package com.wuwii.observer;

/**
 * Created by KronChan on 18/6/26 上午10:24.
 */
public class ObserverMain {
    public static void main(String[] args) {
        Observer buyObserver = new BuyObserver();
        Observer couponOberver = new CouponOberver();
        Observer recommendOberver = new RecommendOberver();
        ProductObservable productObservable = new ProductObservable(buyObserver, couponOberver, recommendOberver);
        productObservable.BuyProductSuccessfully("Computer");
        productObservable.BuyProductSuccessfully("mouse");

    }
}
