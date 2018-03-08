package com.now.learn.construct.day02;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：〈食品工厂〉
 *
 * @author lyh
 * create on 2018/3/7
 * @version 1.0
 */
public class MakeFoodFactory {

    private ReentrantLock lock = new ReentrantLock(true);
    //生产者状态监听
    Condition customerCondition = lock.newCondition();
    //消费者状态监听
    Condition produceCondition = lock.newCondition();
    //工厂的最大容量
    BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>(10);

    /**
     * 生产行为控制
     * @author lyh
     * Created On 2018/3/7 下午5:14
     */
    public void produce(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"获得锁"+blockingQueue.size());//添加打印
            while (blockingQueue.size()>=3) {//当前队列中的面包数大于3的时候，暂停生产面包
                produceCondition.await();
                System.out.println(Thread.currentThread().getName()+"await"+blockingQueue.size());//添加打印
            }
            blockingQueue.put("添加一个面包");
            System.out.println(Thread.currentThread().getName()+"生产一个面包,当前面包数"+blockingQueue.size());
            Thread.sleep(1000);
            //if(blockingQueue.size()>=3) {
            customerCondition.signalAll(); //一旦有面包就喊一下消费者区消费
            //}
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放锁");
            lock.unlock();

        }
    }

    /**
     * 消费者行为控制
     * @author lyh
     * Created On 2018/3/7 下午5:14
     */
    public void customer(){
        try{
            lock.lock();
            while(blockingQueue.isEmpty()){  //当队列中没有面包的时候，等待
                customerCondition.await();
            }
            blockingQueue.take();
            System.out.println(Thread.currentThread().getName()+"消费一个面包,当前面包数" +blockingQueue.size());
            Thread.sleep(1000);
            produceCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
