package com.java8.thread;

import java.util.concurrent.*;

public class CyclicBarrierTest {
    private static final int QUEUE_CAPACITY = 500000;

    private static ExecutorService service = new ThreadPoolExecutor(5, 8,
            100, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(QUEUE_CAPACITY),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {


        //final  CyclicBarrier cb = new CyclicBarrier(2);//创建CyclicBarrier对象并设置3个公共屏障点


        final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("********我最先执行***********");
            }
        });
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));

                        System.out.println("线程" + Thread.currentThread().getName() +
                                "即将到达集合地点1，当前已有" + cb.getNumberWaiting() + "个已经到达，正在等候");


                        cb.await();//到此如果没有达到公共屏障点，则该线程处于等待状态，如果达到公共屏障点则所有处于等待的线程都继续往下运行

                        Thread.sleep((long) (Math.random() * 10000));

                        System.out.println("线程" + Thread.currentThread().getName() +
                                "即将到达集合地点2，当前已有" + cb.getNumberWaiting() + "个已经到达，正在等候");


                        cb.await();    //这里CyclicBarrier对象又可以重用

                        Thread.sleep((long) (Math.random() * 10000));

                        System.out.println("线程" + Thread.currentThread().getName() +
                                "即将到达集合地点3，当前已有" + cb.getNumberWaiting() + "个已经到达，正在等候");


                        cb.await();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };


            service.execute(runnable);
        }

        service.shutdown();
    }
}
