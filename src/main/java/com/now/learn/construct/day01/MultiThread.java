package com.now.learn.construct.day01;

import javax.swing.plaf.multi.MultiInternalFrameUI;

/**
 * 描述：〈多个线程多个锁〉
 * 不同对象持有各自的锁
 * @author liyaohua
 * create on 2018/3/1
 * @version 1.0
 */
public class MultiThread {

    private int number = 0;

    private void println(String msg){
        System.out.println(msg);
    }

    private synchronized void printNumber(String tag){
        if(tag.equals("a")){
            number = 50;
            println("tag a set number over! ");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            number = 100;
            println("tag b set number over! ");
        }
        System.out.println("number:"+number);
    }

    public static void main(String[] args){
        final MultiThread multiThread1 = new MultiThread();
        final MultiThread multiThread2 = new MultiThread();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                    multiThread1.printNumber("a");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                multiThread1.printNumber("b");
            }
        });
        t1.start();
        t2.start();
    }
}
