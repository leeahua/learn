package com.now.learn.construct.day02;

/**
 * 描述：〈ReentrantLock测试〉
 *
 * @author liyaohua
 * create on 2018/3/7
 * @version 1.0
 */
public class ReentrantLockDemo2 {




      class MyReentrantLockRunnableA implements Runnable{
        private BusinessService businessService;

        MyReentrantLockRunnableA(BusinessService businessService){
            this.businessService = businessService;
        }

          @Override
          public void run() {
              businessService.awaitA();
          }
      }
    class MyReentrantLockRunnableB implements Runnable{
        private BusinessService businessService;

        MyReentrantLockRunnableB(BusinessService businessService){
            this.businessService = businessService;
        }

        @Override
        public void run() {
            businessService.awaitA();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo2 reentrantLockDemo = new ReentrantLockDemo2();
        BusinessService businessService = new BusinessService();
        MyReentrantLockRunnableA runnableA = reentrantLockDemo.new MyReentrantLockRunnableA(businessService);
        MyReentrantLockRunnableB runnableB = reentrantLockDemo.new MyReentrantLockRunnableB(businessService);
        Thread t1 = new Thread(runnableA,"t1");
        Thread t2 = new Thread(runnableB,"t2");

        t1.start();
        t2.start();

        Thread.sleep(2000);

        businessService.signalA();

        Thread.sleep(1000);
        businessService.singnalB();
    }


}
