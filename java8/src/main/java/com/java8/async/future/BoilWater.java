package com.java8.async.future;


import java.util.concurrent.Callable;

/**
 * @author chenyihui
 * @Title: BoilWater
 * @Description: 烧水 T1线程
 * @date 2020/05/26
 */
public class BoilWater implements Callable<String> {


    @Override
    public String call() throws Exception {
        System.out.println(" T1: 洗水壶 ");
        Thread.sleep(1000);
        System.out.println(" T1: 烧水 ");
        Thread.sleep(10000);

        return "T1: 水烧开了";
    }
}
