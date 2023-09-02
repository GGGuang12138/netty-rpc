package com.gg.server.service.impl;

import com.gg.pojo.service.RpcService;
import com.gg.pojo.User;

/**
 * @author Alan
 * @Description
 * @date 2023.08.07 18:45
 */
public class RpcServiceImpl implements RpcService {
    @Override
    public User login(String name, Integer age) {
        System.out.println("login: " + name + " " + age);
        User user = new User("hello " + name, age);
        return user;
    }

    @Override
    public User findUserById(Integer id) {
        System.out.println("findUserById: " + id);
        return new User("XXX"+id,3);
    }

}
