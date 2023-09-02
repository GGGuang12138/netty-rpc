package com.gg.client;

import com.gg.pojo.service.RpcService;
import com.gg.pojo.RpcServiceHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.Proxy;

/**
 * @author Alan
 * @Description
 * @date 2023.08.06 12:07
 */
public class ClientProxyFactory {

    private ClientMsgHandler handler = new ClientMsgHandler();

    public ClientProxyFactory(String IP,int port) throws InterruptedException {
        // 初始化和服务端的连接
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast("ObjectEncoder", new ObjectEncoder())
                                .addLast("ObjectDecoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingResolver(null)))
                                .addLast("customClientHandler",handler);
                    }
                });
        bootstrap.connect(IP,port).sync();

    }

    public  RpcService getRpcService() {
        return (RpcService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{RpcService.class}, new RpcServiceHandler(handler));
    }
}
