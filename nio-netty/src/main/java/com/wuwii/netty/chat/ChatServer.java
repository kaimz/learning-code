package com.wuwii.netty.chat;

import com.wuwii.netty.core.BaseServerTemplate;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author kai.zhang
 * @date 2019/12/10 17:55
 */
public class ChatServer extends BaseServerTemplate {

    public ChatServer(int port) {
        super(port);
    }

    public static void main(String[] args) {
        new ChatServer(8095).serverTask(
                // 处理沾包
                new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()),
                new StringDecoder(),
                new StringEncoder(),
                new ChatServerHandler()
        );

    }

    @Override
    protected ChannelHandler handler(ChannelHandler... channelHandlers) {
        return new ChatServerInitializer();
    }
}
