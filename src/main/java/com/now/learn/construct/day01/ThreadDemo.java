package com.now.learn.construct.day01;

/**
 * 描述：〈〉
 * synchronized 锁的是对象
 * @author liyaohua
 * create on 2018/3/6
 * @version 1.0
 */
public class ThreadDemo {
    /**
     * 竞态方法 method1
     *
     * */
    public  synchronized void method1(){
        try{
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+"  over");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 普通方法 method2
     *
     * */
    public   void method2(){
        System.out.println(Thread.currentThread().getName()+"  normal  method");
    }

    /**
     * 竞态方法 method3
     *
     * */
    public  synchronized void method3(){
        try{
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+"  over");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static  void main(String[] args){
        final ThreadDemo threadDemo = new ThreadDemo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadDemo.method1();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadDemo.method2();
                threadDemo.method3();
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
