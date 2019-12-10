package com.wuwii.netty.pojo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author kai.zhang
 * @date 2019/12/10 15:32
 */
public class PojoClientHandler extends SimpleChannelInboundHandler<UnixTime> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, UnixTime msg) throws Exception {
        System.out.println(msg);
    }
}
