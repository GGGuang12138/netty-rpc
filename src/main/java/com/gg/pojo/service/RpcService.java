package com.gg.pojo.service;

import com.gg.pojo.User;

/**
 * @author Alan
 * @Description
 * @date 2023.08.06 12:04
 */
public interface RpcService {

    User login(String name, Integer age);

    User findUserById(Integer id);
}
