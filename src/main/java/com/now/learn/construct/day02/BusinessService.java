package com.now.learn.construct.day02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：〈〉
 *
 * @author liyaohua
 * create on 2018/3/7
 * @version 1.0
 */
public class BusinessService {

    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition conditionA = reentrantLock.newCondition();
    private Condition conditionB = reentrantLock.newCondition();


    public void awaitA(){
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+"进入awaitA方法");
            long timeBefore = System.currentTimeMillis();
            conditionA.await();
            long timeAfter = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+"被唤醒");
            System.out.println(Thread.currentThread().getName()+"等待了"+(timeAfter-timeBefore)/1000+"s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
    public void awaitB(){
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+"进入awaitB方法");
            long timeBefore = System.currentTimeMillis();
            conditionB.await();
            long timeAfter = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+"被唤醒");
            System.out.println(Thread.currentThread().getName()+"等待了"+(timeAfter-timeBefore)/1000+"s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public void signalA(){
        try {
            reentrantLock.lock();
            System.out.println("A被唤醒");
            conditionA.signalAll();
        }finally {
            reentrantLock.unlock();
        }
    }

    public void singnalB(){
        try {
            reentrantLock.lock();
            System.out.println("B被唤醒");
            conditionB.signalAll();
        }finally {
            reentrantLock.unlock();
        }
    }

}
