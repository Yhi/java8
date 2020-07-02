package com.java8.async.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author chenyihui
 * @Title: TeaTest2
 * @Description:
 * @date 2020/05/26
 */
public class TeaTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        BoilWater boilWater=new BoilWater();//烧水
        FutureTask<String> futureTask1=new FutureTask<>(boilWater);
        ReadyTeaSet readyTeaSet=new ReadyTeaSet(futureTask1);//准备茶具
        FutureTask<String> futureTask2=new FutureTask<>(readyTeaSet);

        new Thread(futureTask1).start();
        Thread.sleep(2000);
        new Thread(futureTask2).start();
        Thread.sleep(2000);
        System.out.println(futureTask2.get());
    }
}
