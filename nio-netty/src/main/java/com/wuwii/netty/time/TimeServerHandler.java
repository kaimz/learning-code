package com.wuwii.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author kai.zhang
 * @date 2019/12/9 21:55
 */
@ChannelHandler.Sharable
public class TimeServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        //channelActive 在建立连接,准备通信的时候被调用
        // 构建一个 4 字节, 32 位整数的消息
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 123456789L));
        // 因为 netty 里面所有的操作都是异步的,所有的操作不是立即执行的,这里返回一个 还没有发生 I/O 操作
        final ChannelFuture f = ctx.writeAndFlush(time);
        // 由于 Netty 里面所有逇操作都是异步的, 直接 close channel 会导致消息还没发送,就关闭连接了,我们需要一个监听者,在它写操作完成后,通知我们去关闭通信连接
        f.addListener(ChannelFutureListener.CLOSE);
        // 下面是自定义的监听器
        /*f.addListener((ChannelFutureListener) future -> {
            assert f == future;
            ctx.close();
        });*/
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

    }
}
