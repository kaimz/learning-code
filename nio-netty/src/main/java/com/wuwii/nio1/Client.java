package com.wuwii.nio1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * @author KronChan
 * @date 2019-04-07 22:08
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SocketChannel socket = SocketChannel.open();
                        socket.configureBlocking(false);
                        socket.connect(new InetSocketAddress("127.0.0.1", 6789));
                        while (!socket.finishConnect()) {
                            System.out.println("等待连接中，" + System.currentTimeMillis());
                        }
                        System.out.println("连接完成，等待写入" + System.currentTimeMillis());
                        TimeUnit.SECONDS.sleep(2);
                        String msg = Thread.currentThread().getName();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(msg.length());
                        byteBuffer.put(msg.getBytes());
                        byteBuffer.flip();
                        socket.write(byteBuffer);
                        byteBuffer.clear();
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            //TimeUnit.HOURS.sleep(1);
        }
    }
}
