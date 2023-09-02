package com.gg.client;

import com.gg.pojo.MyRequest;
import com.gg.pojo.User;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Alan
 * @Description
 * @date 2023.08.06 17:49
 */
public class ClientMsgHandler extends ChannelInboundHandlerAdapter {

    private Channel channel;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        System.out.println("接收到消息 " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("当前连接建立");
        this.channel = ctx.channel();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("当前连接断开");
        channel = null;
    }

    public void sendRpcRequest(MyRequest myRequest) {
        if (this.channel == null){
            throw new RuntimeException("连接已断开");
        }
        channel.writeAndFlush(myRequest);
    }
}
