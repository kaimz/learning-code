package com.wuwii.netty.echo;

import com.wuwii.netty.core.BaseServerTemplate;

/**
 * @author kai.zhang
 * @date 2019/12/9 20:40
 */
public class EchoServer extends BaseServerTemplate {

    public EchoServer(int port) {
        super(port);
    }

    public static void main(String[] args) {
        new EchoServer(8090).serverTask(new EchoServerHandler());
    }
}
