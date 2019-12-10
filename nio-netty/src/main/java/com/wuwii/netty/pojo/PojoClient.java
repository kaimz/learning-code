package com.wuwii.netty.pojo;

import com.wuwii.netty.core.BaseClientTemplate;

/**
 * @author kai.zhang
 * @date 2019/12/10 15:33
 */
public class PojoClient extends BaseClientTemplate {
    public PojoClient(String host, int port) {
        super(host, port);
    }

    public static void main(String[] args) {
        new PojoClient("localhost", 8094).clientTask(new TimeDecoder(), new PojoClientHandler());
    }
}
