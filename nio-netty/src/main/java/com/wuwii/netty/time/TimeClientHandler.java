package com.wuwii.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author kai.zhang
 * @date 2019/12/9 20:43
 */
public class TimeClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        long currentTimeMillis = (byteBuf.readUnsignedInt() - 123456789L) * 1000L;
        System.out.println(new Date(currentTimeMillis));
        channelHandlerContext.close();
    }

}
