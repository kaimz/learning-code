package com.wuwii.netty.core;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author kai.zhang
 * @date 2019/12/10 12:25
 */
public abstract class BaseClientTemplate {
    private final int port;

    private final String host;

    public BaseClientTemplate(String host, int port) {
        this.port = port;
        this.host = host;
    }

    public void clientTask(ChannelHandler... channelHandlers) {
        // 客户端的 workGroup
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            // Bootstrap 是对非服务端使用
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    // 客户端使用的 是 NioSocketChannel, 在客户端创建的时候使用
                    .channel(NioSocketChannel.class)
                    // 设置 channel 的属性
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    // 设置 handler
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(channelHandlers);
                        }
                    });
            // 启动客户端
            ChannelFuture future = bootstrap.connect(host, port).sync();
            // 关闭连接
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }
}
