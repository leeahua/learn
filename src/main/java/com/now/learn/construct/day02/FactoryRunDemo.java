package com.now.learn.construct.day02;

/**
 * 描述：〈〉
 *
 * @author liyaohua
 * create on 2018/3/7
 * @version 1.0
 */
public class FactoryRunDemo {




    protected class RunnableA implements Runnable{
        final FactoryBusiness factoryBusiness;
        RunnableA(FactoryBusiness factoryBusiness){
            this.factoryBusiness = factoryBusiness;
        }
        @Override
        public void run() {
           for(;;){
               factoryBusiness.excuteA();
           }

        }
    }

    protected class RunnableB implements Runnable{
        final FactoryBusiness factoryBusiness;
        RunnableB(FactoryBusiness factoryBusiness){
            this.factoryBusiness = factoryBusiness;
        }
        @Override
        public void run() {
            for(;;){
                factoryBusiness.excuteB();
            }

        }
    }

    protected class RunnableC implements Runnable{
        final FactoryBusiness factoryBusiness;
        RunnableC(FactoryBusiness factoryBusiness){
            this.factoryBusiness = factoryBusiness;
        }
        @Override
        public void run() {
            for(;;){
                factoryBusiness.excuteC();
            }

        }
    }


    public static void main(String[] args){
        FactoryRunDemo factoryRunDemo = new FactoryRunDemo();
        FactoryBusiness factoryBusiness = new FactoryBusiness();
        RunnableA runnableA = factoryRunDemo.new RunnableA(factoryBusiness);
        RunnableB runnableB = factoryRunDemo.new RunnableB(factoryBusiness);
        RunnableC runnableC = factoryRunDemo.new RunnableC(factoryBusiness);
        Thread t1 = new Thread(runnableA,"t1");
        Thread t2 = new Thread(runnableB,"t2");
        Thread t3 = new Thread(runnableC,"t3");
        t1.start();
        t2.start();
        t3.start();
    }


}
