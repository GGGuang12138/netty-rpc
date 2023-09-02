package com.gg.pojo;

import com.gg.client.ClientMsgHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author Alan
 * @Description
 * @date 2023.08.06 12:04
 */

public class RpcServiceHandler implements InvocationHandler {

    private ClientMsgHandler clientMsgHandler;

    public RpcServiceHandler(ClientMsgHandler clientMsgHandler) {
        this.clientMsgHandler = clientMsgHandler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        // 动态代理调用远程方法
        System.out.println("proxy: " + proxy.getClass().getName());
        System.out.println("method: " + method.getName());
        System.out.println("args: " + Arrays.toString(args));
        MyRequest myRequest = new MyRequest();
        myRequest.setReqId(UUID.randomUUID().toString());
        myRequest.setClassName(method.getDeclaringClass().getName());
        myRequest.setMethodName(method.getName());
        myRequest.setParameterTypes(method.getParameterTypes());
        myRequest.setParameters(args);
        this.clientMsgHandler.sendRpcRequest(myRequest);
        return new User();
    }
}
