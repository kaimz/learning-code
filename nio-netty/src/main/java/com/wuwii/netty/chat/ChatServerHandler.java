package com.wuwii.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author kai.zhang
 * @date 2019/12/10 17:56
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 存储连接进来的客户端
     */
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 每当服务端收到新的客户端连接的时候调用,
        Channel channel = ctx.channel();
        channel.writeAndFlush("[client - " + channel.remoteAddress() + " 加入聊天]\n");
        clients.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 每当服务端收到客户端断开连接的时候
        Channel channel = ctx.channel();
        channel.writeAndFlush("client - " + channel.remoteAddress() + "退出了聊天室\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 如果当前的 channel 出现异常,则断开连接
        cause.printStackTrace();
        ctx.close();
        System.out.println("[Client:" + ctx.channel().remoteAddress() + "]断线");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 监听到客户端活动
        Channel in = ctx.channel();
        System.out.println("[Client:" + in.remoteAddress() + "]上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 监听到客户端不活动
        System.out.println("[Client:" + ctx.channel().remoteAddress() + "]下线");
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        // 自己本身的 channel
        Channel in = ctx.channel();
        for (Channel client : clients) {
            if (client != in) {
                client.writeAndFlush(String.format("[%s]:%s\n", in.remoteAddress(), msg));
            } else {
                client.writeAndFlush(String.format("[you]:%s\n", msg));
            }
        }
    }
}
