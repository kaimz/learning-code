package com.wuwii.observer;

/**
 * Created by KronChan on 18/6/26 上午10:23.
 */
public class RecommendOberver implements Observer {
    @Override
    public void receive(String product) {
        System.out.println(String.format("Here are recommending you some other %s.", product));
    }
}
