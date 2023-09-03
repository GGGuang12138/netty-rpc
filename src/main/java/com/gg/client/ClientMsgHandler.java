package com.gg.client;

import com.gg.pojo.MyRequest;
import com.gg.pojo.MyResponse;
import com.gg.pojo.SyncResponse;
import com.gg.pojo.User;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Alan
 * @Description
 * @date 2023.08.06 17:49
 */
public class ClientMsgHandler extends ChannelInboundHandlerAdapter {

    private Channel channel;

    private final Map<String, SyncResponse> resultMap = new ConcurrentHashMap<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        System.out.println("接收到消息 " + msg);
        // 异步接收结果
        MyResponse response = (MyResponse) msg;
        String reqId = response.getRspId();
        // 将异步获取到的结果转到同步调用对象中
        resultMap.get(reqId).setResult(response.getRes());
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

    public SyncResponse sendRpcRequest(MyRequest myRequest) {
        if (this.channel == null){
            throw new RuntimeException("连接已断开");
        }
        // 新建一个同步响应结果
        SyncResponse syncResponse = new SyncResponse(myRequest.getReqId());
        resultMap.put(myRequest.getReqId(), syncResponse);
        // 异步去发送请求
        channel.writeAndFlush(myRequest);
        return syncResponse;
    }
}
