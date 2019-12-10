package com.wuwii.netty.time;

import com.wuwii.netty.core.BaseClientTemplate;

/**
 * @author kai.zhang
 * @date 2019/12/9 20:51
 */
public class TimeClient extends BaseClientTemplate {


    public TimeClient(String host, int port) {
        super(host, port);
    }

    public static void main(String[] args) {
        new TimeClient("localhost", 8092).clientTask(new TimeClientHandler());
    }
}
