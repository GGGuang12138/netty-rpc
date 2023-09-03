package com.gg.pojo;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Alan
 * @Description
 * @date 2023.09.03 12:13
 */
public class SyncResponse {

    private String reqId;
    private Object result;
    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public SyncResponse(String reqId) {
        this.reqId = reqId;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public Object getResult() {
        // todo 为什么加锁
        lock.lock();
        try {
            if ( result != null) {
                return result;
            }
            // 阻塞同时释放锁
            condition.await();
            return result;
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return result;
    }

    public void setResult(Object result) {
        lock.lock();
        try {
            this.result = result;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
