package com.now.learn.construct.day01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * 描述：〈售票模拟〉
 *
 * @author liyaohua
 * create on 2018/3/6
 * @version 1.0
 */
public class SellTicketMock {


    public static void main(String[] args){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(100);
        for(int i =1 ;i<=100;i++){
            blockingQueue.add(i);
        }
        CountDownLatch countDownLatch = new CountDownLatch(blockingQueue.size());



        while(countDownLatch.getCount()!=0){

        }
    }
}
