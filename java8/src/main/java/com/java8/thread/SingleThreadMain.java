package com.java8.thread;

import java.util.concurrent.*;


public class SingleThreadMain {


    public static void main(String[] args) {


        ExecutorService executor= Executors.newSingleThreadExecutor();

        executor.submit(()->{

            String threadName=Thread.currentThread().getName();
            System.out.println("Hello"+threadName);
        });
        //java进程永远不会停止！ 执行者必须明确地停止 - 否则他们会继续监听新的任务。
        // ExecutorService为此目的提供了两种方法：shutdown（）等待当前运行的任务完成，而shutdownNow（）中断所有正在运行的任务并立即关闭执行器

        System.out.println(" shutdown executor ");
       // executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }/*finally {
            if (!executor.isTerminated()){
                System.err.println("nono-finished tasks");
            }

            executor.shutdownNow();
            System.out.println("shutdown finished");
        }*/

    }
}
