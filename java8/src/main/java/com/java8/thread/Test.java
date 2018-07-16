package com.java8.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));


        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            executor.submit(myTask);
            System.out.println("线程池中线程数据：" + executor.getPoolSize() + ",");
            System.out.println("队列中等待执行的任务数目:" + executor.getQueue().size() + ",");
            System.out.println("已执行完毕的任务数据：" + executor.getCompletedTaskCount());
        }

        executor.shutdown();

    }
}



