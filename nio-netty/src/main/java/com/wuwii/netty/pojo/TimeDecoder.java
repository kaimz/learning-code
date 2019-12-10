package com.wuwii.netty.pojo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author kai.zhang
 * @date 2019/12/10 15:28
 */
public class TimeDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 接收 int 类型 4 字节整形
        if (in.readableBytes() < 4) {
            return;
        }
        out.add(new UnixTime(in.readUnsignedInt()));
    }
}
