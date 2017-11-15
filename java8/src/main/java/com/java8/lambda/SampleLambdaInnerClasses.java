package com.java8.lambda;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.*;

/**
 * Created by cyh on 2016-04-16.
 * Exercise 1：Deodorizing Inner-classes:
 *  Single Abstract Method Interfaces
 *  Functional Interfaces
 */
public  class SampleLambdaInnerClasses {

    public static void main(String[] arg) throws Exception{
        ExecutorService executorService8= Executors.newFixedThreadPool(10);
        ExecutorService executorService7= Executors.newFixedThreadPool(10);
        SampleLambdaInnerClasses.openThredPoolJav8(executorService8);
        System.out.println("----------------------");
        SampleLambdaInnerClasses.openThredPoolJava7(executorService7);
    }

    public  static  void openThredPoolJava7(ExecutorService executorService){
        for (int i=0;i<10;i++){
            final   int index=i;
            executorService.submit(new Runnable() {
                public void run() {
                    System.out.println("Running task "+index);
                }
            });
        }
        System.out.println("Tasks started...");
        executorService.shutdown();
    }
    //1.lambda表达式格式：(参数) -> 表达式
    public static void openThredPoolJav8(ExecutorService executorService){
        IntStream.range(0,10).
                forEach(i->executorService.submit(()->
                        System.out.println("Running task..."+i)));

        System.out.println("Tasks started...");
        executorService.shutdown();
    }


}
