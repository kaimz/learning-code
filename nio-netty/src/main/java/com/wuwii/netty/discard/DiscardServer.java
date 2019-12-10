package com.wuwii.netty.discard;

import com.wuwii.netty.core.BaseServerTemplate;

/**
 * @author kai.zhang
 * @date 2019/11/29 21:30
 */
public class DiscardServer extends BaseServerTemplate {

    public DiscardServer(int port) {
        super(port);
    }

    public static void main(String[] args) {
        new DiscardServer(8099).serverTask(new DisCardServerHandler());
    }
}
