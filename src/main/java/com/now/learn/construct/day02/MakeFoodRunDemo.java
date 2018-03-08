package com.now.learn.construct.day02;

/**
 * 工人生产控制
 * @author lyh
 * create on 2018/3/7
 * @version 1.0
 */
public class MakeFoodRunDemo {

    /**
     *  生产面包
     * Created On 2018/3/7 下午4:04
     */
    protected class FoodCustomer implements Runnable{
        private MakeFoodFactory makeFoodFactory;

        FoodCustomer(MakeFoodFactory makeFoodFactory){
            this.makeFoodFactory = makeFoodFactory;
        }

        @Override
        public void run() {
            for(;;){
                makeFoodFactory.customer();
            }
        }
    }
    /**
     *  消费面包
     * Created On 2018/3/7 下午4:05
     */
    protected class FoodProducer implements Runnable{
        private MakeFoodFactory makeFoodFactory;

        FoodProducer(MakeFoodFactory makeFoodFactory){
            this.makeFoodFactory = makeFoodFactory;
        }

        @Override
        public void run() {
            for(;;){
                makeFoodFactory.produce();
            }
        }
    }

    public static void main(String[] args){
        MakeFoodFactory makeFoodFactory = new MakeFoodFactory();
        MakeFoodRunDemo makeFoodRunDemo = new MakeFoodRunDemo();
        FoodProducer producer = makeFoodRunDemo.new FoodProducer(makeFoodFactory);
        FoodCustomer foodCustomer = makeFoodRunDemo.new FoodCustomer(makeFoodFactory);
        Thread t1 = new Thread(producer,"p1");
        Thread t2 = new Thread(producer,"p2");
        Thread t3 = new Thread(foodCustomer,"c1");
        Thread t4 = new Thread(foodCustomer,"c2");

        t2.start();
        t1.start();
        t3.start();
        //t4.start();
    }

}
