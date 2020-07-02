package com.java8.async.future;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author chenyihui
 * @Title: BoilWater
 * @Description: 准备茶具 T2线程
 * @date 2020/05/26
 */
public class ReadyTeaSet  implements Callable<String> {

    private FutureTask<String> futureTask=null;

    public ReadyTeaSet(FutureTask<String> futureTask){
        this.futureTask=futureTask;
    }


    @Override
    public String call() throws Exception {
        System.out.println(" T2: 吸水杯 ");
        Thread.sleep(1000);
        System.out.println(" T2: 洗茶壶 ");
        Thread.sleep(2000);
        System.out.println(" T2: 取茶叶 ");
        Thread.sleep(1000);
        System.out.println("T2： 等着水烧开");
        System.out.println(futureTask.get());
        return "一壺好茶";
    }
}
