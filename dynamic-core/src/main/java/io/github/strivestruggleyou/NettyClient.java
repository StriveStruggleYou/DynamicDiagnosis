package io.github.strivestruggleyou;

import io.github.strivestruggleyou.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.net.InetSocketAddress;

public class NettyClient {

    private Channel channel;

    EventLoopGroup worker;

    public void connect() {
        worker = new NioEventLoopGroup();

        try {
            //辅助启动类
            Bootstrap bootstrap = new Bootstrap();

            //设置线程池
            bootstrap.group(worker);

            //设置socket工厂
            bootstrap.channel(NioSocketChannel.class);

            //设置管道
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //获取管道
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    //字符串解码器
                    pipeline.addLast(new StringDecoder());
                    //字符串编码器
                    pipeline.addLast(new StringEncoder());
                    //处理类
                    pipeline.addLast(new ClientHandler());
                }
            });

            //发起异步连接操作
            ChannelFuture futrue = bootstrap.connect(new InetSocketAddress("127.0.0.1", 9527))
                    .sync();

            channel = futrue.channel();
            //等待客户端链路关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void closeConnect() {
        try {
            if (channel != null) {
                channel.closeFuture().sync();
            }
            if (worker != null) {
                worker.shutdownGracefully();
            }
        } catch (Exception e) {
            System.out.println("close connect e:" + e);
        }
    }


    public void send(String msg) {
        channel.writeAndFlush(msg);
    }


    public static void main(String args[]) {
        NettyClient nettyClient = new NettyClient();

        nettyClient.connect();

        nettyClient.send("line com.qipeng.blackcat.web.TrackRecognitionController testShow 24 logger.info(\"test\");");


    }

}
