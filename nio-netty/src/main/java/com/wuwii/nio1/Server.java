package com.wuwii.nio1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author KronChan
 * @date 2019-04-07 22:08
 */
public class Server {
    public static void main(String[] args) throws IOException {
        final int port = 6789;
        // 1. 打开 ServerSocketChannel 用来监听客户端连接请求
        ServerSocketChannel channel = ServerSocketChannel.open();
        ServerSocket socket = channel.socket();
        socket.bind(new InetSocketAddress(port));
        // 2. 设置非祖塞模式
        channel.configureBlocking(false);
        Selector selector = Selector.open();
        // 3. 将 ServerSocketChannel 注册到 Selector 上，并且监听 ACCEPT 事件
        channel.register(selector, SelectionKey.OP_ACCEPT);

        // 缓冲区，设置大小 1M
        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            int select = selector.select();
            // 4. 判断是否就绪，如果什么消息也没有则什么也不做
            if (select == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            // 5. 如果有消息， 处理新的请求，
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (!selectionKey.isValid()) {
                    continue;
                }
                // 6. 判断是否有可以接受的连接，有的话创建连接
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel acceptChannel = (ServerSocketChannel) selectionKey.channel();
                    // 7. 创建客户端连接连接，并且设置非阻塞模式，将连接注册到 Selector 上，监听读操作，
                    SocketChannel acceptSocket = acceptChannel.accept();
                    acceptSocket.configureBlocking(false);
                    acceptSocket.register(selector, SelectionKey.OP_READ);
                }
                // 8. 判断该消息是否可以读
                else if (selectionKey.isReadable()) {
                    //9. 开始读取数据
                    SocketChannel readChannel = (SocketChannel) selectionKey.channel();
                    byteBuffer.clear();
                    while (readChannel.read(byteBuffer) > 0) {
                        byteBuffer.flip();
                        while (byteBuffer.hasRemaining()) {
                            // 解码
                            byte[] bytes = new byte[byteBuffer.remaining()];
                            byteBuffer.get(bytes);
                            String message = new String(bytes, StandardCharsets.UTF_8);
                            System.out.println(message);
                        }
                    }
                    byteBuffer.clear();
                }
            }
        }
    }
}
