package com.wuwii.netty.pojo;

import com.wuwii.netty.core.BaseServerTemplate;

/**
 * @author kai.zhang
 * @date 2019/12/10 15:33
 */
public class PojoServer extends BaseServerTemplate {
    public PojoServer(int port) {
        super(port);
    }

    public static void main(String[] args) {
        new PojoServer(8094).serverTask(new TimeEncoder(), new PojoServerHandler());
    }
}
