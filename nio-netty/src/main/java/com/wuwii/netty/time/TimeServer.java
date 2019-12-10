package com.wuwii.netty.time;

import com.wuwii.netty.core.BaseServerTemplate;

/**
 * @author kai.zhang
 * @date 2019/12/9 21:55
 */
public class TimeServer extends BaseServerTemplate {
    public TimeServer(int port) {
        super(port);
    }

    public static void main(String[] args) {
        new TimeServer(8092).serverTask(new TimeServerHandler());
    }
}
