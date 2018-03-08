package com.now.learn.construct.day02;


import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：〈ReentrantLock测试〉
 *
 * @author liyaohua
 * create on 2018/3/7
 * @version 1.0
 */
public class ReentrantLockDemo {

    protected class MyReentrantLockRunnable implements Runnable{
        final ReentrantLock reentrantLock = new ReentrantLock();
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "就绪");
            reentrantLock.lock();
            try {
                for(int i=0;i<2;i++){
                    System.out.println(Thread.currentThread().getName() + "执行:"+i);
                }
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "完毕");
        }
    }

    public static void main(String[] args){
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        MyReentrantLockRunnable runnable = reentrantLockDemo.new MyReentrantLockRunnable();
        Thread t1 = new Thread(runnable,"t1");
        Thread t2 = new Thread(runnable,"t2");
        t1.start();
        t2.start();
    }
}
