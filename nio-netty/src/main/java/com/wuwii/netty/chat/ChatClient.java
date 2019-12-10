package com.wuwii.netty.chat;

import com.wuwii.netty.core.BaseClientTemplate;
import io.netty.channel.Channel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author kai.zhang
 * @date 2019/12/10 19:35
 */
public class ChatClient extends BaseClientTemplate {
    public ChatClient(String host, int port) {
        super(host, port);
    }

    public static void main(String[] args) {
        new ChatClient("localhost", 8095).clientTask(
                // 处理沾包
                new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()),
                new StringDecoder(),
                new StringEncoder(),
                new ChatClientHandler()
        );
    }

    @Override
    protected void op(Channel channel) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                channel.writeAndFlush(in.readLine() + "\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
