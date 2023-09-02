package com.gg.pojo;

import java.io.Serializable;

/**
 * @author Alan
 * @Description
 * @date 2023.08.06 12:04
 */
public class User implements Serializable {

    private static final long serialVersionUID = 5921604958888198131L;

    private String name;

    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
