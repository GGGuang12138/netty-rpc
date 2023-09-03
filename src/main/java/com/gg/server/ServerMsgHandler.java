package com.gg.server;

import com.gg.pojo.MyRequest;
import com.gg.pojo.MyResponse;
import com.gg.pojo.service.RpcService;
import com.gg.server.service.impl.RpcServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Alan
 * @Description
 * @date 2023.08.06 19:20
 */
public class ServerMsgHandler extends ChannelInboundHandlerAdapter {

    private final ThreadPoolExecutor poolExecutor
            = new ThreadPoolExecutor(5,10,60, TimeUnit.SECONDS,new LinkedBlockingDeque<>(50));

    private Map<String,Object> serviceMap = new ConcurrentHashMap<>();

    public ServerMsgHandler() {
        serviceMap.put(RpcService.class.getName(),new RpcServiceImpl());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        System.out.println("server rec " + msg.toString());
        MyRequest myRequest = (MyRequest) msg;
        // 交给线程池处理
        poolExecutor.submit(() -> {
            String className = myRequest.getClassName();
            Object service = serviceMap.get(className);
            try {
                // 通过反射执行方法
                Method method = service.getClass().getMethod(myRequest.getMethodName(), myRequest.getParameterTypes());
                Object result = method.invoke(service, myRequest.getParameters());
                // 构造返回结果
                MyResponse myResponse = new MyResponse();
                myResponse.setRspId(myRequest.getReqId());
                myResponse.setRes(result);
                ctx.writeAndFlush(myResponse);
                System.out.println("server send " + myResponse.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server 接收连接");
    }
}
