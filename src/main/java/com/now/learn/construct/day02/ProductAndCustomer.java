package com.now.learn.construct.day02;

import java.util.concurrent.*;

/**
 * 描述：〈〉
 * 测试生产者消费者
 * @author liyaohua
 * create on 2018/3/7
 * @version 1.0
 */
public class ProductAndCustomer {

    private static ExecutorService saveThreadPool = new ThreadPoolExecutor(2, 40, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(50000), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     *  生产者消费者操作类
     * Created On 2018/3/7 上午9:00
     */
    protected  class WorkDesk{

        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>(10);
        public void  addDesk() throws InterruptedException{
            blockingQueue.put("生产一个盘子");
        }

        public String useDesk() throws InterruptedException{
           return  blockingQueue.take();
        }

    }

    /**
     *  生产者
     * Created On 2018/3/7 上午9:00
     */
    protected class Producer implements Runnable{
        private String productName;
        private WorkDesk workDesk;

        Producer(String productName,WorkDesk workDesk){
            this.productName = productName;
            this.workDesk = workDesk;
        }
        @Override
        public void run() {
            try{

                while (true){
                    System.out.println(productName+"开始加工");
                    workDesk.addDesk();
                    //生产完一个之后开始休息一秒
                    Thread.sleep(1000);
                }

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    /**
     *  消费者
     * Created On 2018/3/7 上午9:00
     */
    protected class Customer implements Runnable{

        private String customerName;
        private WorkDesk workDesk;

        Customer(String customerName,WorkDesk workDesk){
            this.customerName = customerName;
            this.workDesk = workDesk;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(customerName + "消费一个盘子");
                    workDesk.useDesk();
                    System.out.println(customerName + "待消费数量："+workDesk.blockingQueue.size());
                    Thread.sleep(500);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        ProductAndCustomer productAndCustomer = new ProductAndCustomer();
        WorkDesk workDesk = productAndCustomer.new WorkDesk();

        ExecutorService executorService = Executors.newCachedThreadPool();

        Producer producer1 = productAndCustomer.new Producer("producer1",workDesk);
        Producer producer2 = productAndCustomer.new Producer("producer2",workDesk);
        Producer producer3 = productAndCustomer.new Producer("producer3",workDesk);

        Customer customer1 = productAndCustomer.new Customer("customer1",workDesk);
        Customer customer2 = productAndCustomer.new Customer("customer2",workDesk);

        executorService.submit(producer1);
        executorService.submit(producer2);
        executorService.submit(producer3);
        executorService.submit(customer1);
        executorService.submit(customer2);

    }






}
