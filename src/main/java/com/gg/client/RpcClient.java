package com.gg.client;

import com.gg.pojo.service.RpcService;

/**
 * @author Alan
 * @Description
 * @date 2023.08.06 17:37
 */
public class RpcClient {

    public static void main(String[] args) throws Exception{
        ClientProxyFactory proxyFactory = new ClientProxyFactory("localhost", 8888);
        RpcService rpcService = proxyFactory.getRpcService();
        rpcService.login("gg",123);
    }

}
