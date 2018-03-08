package com.now.learn.construct.day01;

import lombok.Synchronized;

import java.util.concurrent.SynchronousQueue;

/**
 * 描述：〈线程学习1〉
 *synchronized 实现线程同步
 * @author liyaohua
 * create on 2018/3/1
 * @version 1.0
 */
public class MyThread extends Thread{

    private int count = 0;

    @Override
    public void run() {
        //线程核心
        if(Integer.valueOf(Thread.currentThread().getName())%2==0){
            add();
        }else{
            subtraction();
        }

        System.out.println("hello:"+Thread.currentThread().getName()+",count:"+count);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void add() {
        count++;
    }

    private synchronized void subtraction(){
        count--;
    }

    public static void main(String args[]){
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread,"1");
        Thread t2 = new Thread(myThread,"2");
        Thread t3 = new Thread(myThread,"4");
        Thread t4 = new Thread(myThread,"3");
        Thread t5 = new Thread(myThread,"5");
        Thread t6 = new Thread(myThread,"6");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}
