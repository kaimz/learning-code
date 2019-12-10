package com.wuwii.netty.pojo;

import java.util.Date;

/**
 * @author kai.zhang
 * @date 2019/12/10 15:25
 */
public class UnixTime {
    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 123456789);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value - 123456789) * 1000).toString();
    }
}
