package com.wuwii.proxy;

/**
 * Created by KronChan on 18/6/22 上午11:48.
 */
public class HouseProxy implements RentHouse {
    private HouseOwner houseOwner;

    public HouseProxy(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    @Override
    public void rent() {
        houseOwner.rent();
        afterRent();
    }

    /**
     * 租房成功后行为，
     */
    private void afterRent() {
        // 租成功后要付中介费……
    }
}
