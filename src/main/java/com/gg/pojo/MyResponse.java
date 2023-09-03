package com.gg.pojo;

import java.io.Serializable;

/**
 * @author Alan
 * @Description
 * @date 2023.09.03 11:29
 */
public class MyResponse implements Serializable {

    private String rspId;

    private Object res;

    @Override
    public String toString() {
        return "MyResponse{" +
                "rspId='" + rspId + '\'' +
                ", res=" + res +
                '}';
    }

    public String getRspId() {
        return rspId;
    }

    public void setRspId(String rspId) {
        this.rspId = rspId;
    }

    public Object getRes() {
        return res;
    }

    public void setRes(Object res) {
        this.res = res;
    }
}
