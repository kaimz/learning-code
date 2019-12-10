package com.wuwii.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author kai.zhang
 * @date 2019/12/9 20:41
 */
public class EchoServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    public EchoServerHandler() {
        // 设置不自动 release,因为 ctx.writeAndFlush(buff)会使此次ByteBuf回收也即将refCnt置为0
        super(false);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 出现异常断开通信
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println(byteBuf.toString(io.netty.util.CharsetUtil.US_ASCII));
        // 写入接收到的消息
        channelHandlerContext.writeAndFlush(byteBuf);
    }
}
