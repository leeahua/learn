package com.now.learn.construct.day01;

/**
 * 描述：〈〉
 * synchronized 锁的是对象
 * @author liyaohua
 * create on 2018/3/6
 * @version 1.0
 */
public class ThreadDemo3 {
    /**
     * 竞态方法 method1
     *
     * */
    public   void method1(){
        try{
            //TODO 这里是公共区域 不具有竞争属性 可以做很多准备工作
            System.out.println(Thread.currentThread().getName()+"---out the door");
            synchronized(this) {//上锁 关门
                //TODO 这里是竞态区域  需要获取锁才能进来 同样是锁的是对象
                System.out.println(Thread.currentThread().getName()+"---in the door");
                Thread.sleep(5000);
            }
            System.out.println(Thread.currentThread().getName()+"  over");
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static  void main(String[] args){
        final ThreadDemo3 threadDemo1 = new ThreadDemo3();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadDemo1.method1();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadDemo1.method1();
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
