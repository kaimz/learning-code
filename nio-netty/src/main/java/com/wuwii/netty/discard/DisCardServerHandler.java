package com.wuwii.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author kai.zhang
 * @date 2019/11/29 20:39
 */
public class DisCardServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf s) throws Exception {
        System.out.println(s.toString(io.netty.util.CharsetUtil.US_ASCII));
        System.out.flush();
    }
}
