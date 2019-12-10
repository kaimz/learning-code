package com.wuwii.netty.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author kai.zhang
 * @date 2019/12/10 11:14
 */
public abstract class BaseServerTemplate {

    protected final int port;

    public BaseServerTemplate(int port) {
        this.port = port;
    }

    public void serverTask(ChannelHandler... channelHandlers) {
        // bossGroup 用来接收进来的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // boss 接收的连接注册在 worker 上
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // nio 服务启动辅助类
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // 处理一个已经接收的 channel, 自定义事件处理
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            // 添加处理类到 pipeline 上,也就是把我们的丢弃处理加入
                            socketChannel.pipeline().addLast(channelHandlers);
                        }
                    })
                    // 提供 NioServerSocketChannel 用来接收连接的属性设置
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 提供父管道 ServerChannel 接收到连接的属性设置
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口,启动,接收进来的连接
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            // 服务器 socket 关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅退出
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}
