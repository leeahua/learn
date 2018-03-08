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
public class FactoryBusiness {

    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition conditionA = reentrantLock.newCondition();
    private Condition conditionB = reentrantLock.newCondition();
    private Condition conditionC = reentrantLock.newCondition();
    private static int next = 1;

    public void excuteA(){
        try{

            reentrantLock.lock();
            while (next != 1){
                System.out.println(Thread.currentThread().getName()+"A 等待");
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName()+"A开始执行");
            next = 2;
            conditionB.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void excuteB(){
        try{
            reentrantLock.lock();
            while (next != 2){
                System.out.println(Thread.currentThread().getName()+"B等待");
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName()+"B开始执行");
            next = 3;
            conditionC.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void excuteC(){
        try{
            reentrantLock.lock();
            while (next != 3){
                System.out.println(Thread.currentThread().getName()+"C等待");
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName()+"C开始执行");
            next = 1;
            conditionA.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }



}
