package com.now.learn.construct.day02;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：〈食品工厂〉
 * 生产一个消费一个
 * @author liyaohua
 * create on 2018/3/7
 * @version 1.0
 */
public class OrderFoodFactory {

    private ReentrantLock lock = new ReentrantLock(true);
    //生产者状态监听
    Condition customerCondition = lock.newCondition();
    //消费者状态监听
    Condition produceCondition = lock.newCondition();
    //工厂的最大容量
    private static boolean flag = true;

    public void produce(){
        try {
            lock.lock();
            if (!flag) {
                produceCondition.await();
            }
            System.out.println(Thread.currentThread().getName()+"生产一个面包");
            Thread.sleep(1000);
            flag = false;
            customerCondition.signalAll(); //一旦有面包就唤醒消费者区消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void customer(){
        try{
            lock.lock();
            if(flag){  //当队列中没有面包的时候，等待
                customerCondition.await();
            }
            System.out.println(Thread.currentThread().getName()+"消费一个面包");

            Thread.sleep(1000);
            flag = true;
            produceCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



}
