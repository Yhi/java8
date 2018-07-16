package com.java8.pattern.single;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenyihui
 * @version 1.0
 * @Description 当并发访问的时候，第一个调用getInstance方法的线程A，在判断完singleton是null的时候，线程A就进入了if块准备创造实例，
 * 但是同时另外一个线程B在线程A还未创造出实例之前，就又进行了singleton是否为null的判断，这时singleton依然为null，所以线程B也会进入if块去创造实例，
 * 这时问题就出来了，有两个线程都进入了if块去创造实例，结果就造成单例模式并非单例。
 *
 *1.singleton最多只有一个实例，在不考虑反射强行突破访问限制的情况下
 *
 *2.保证了并发访问的情况下，不会发生由于并发而产生的实例
 *
 *3.保证并发的情况下，不会由于初始化动作未完全完成而造成使用了尚未正确初始化的实例
 *
 *4.
 *
 *
 *
 * @date 2018/7/16
 */
public class TestSingleton {

    boolean lock;

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean getLock() {
        return lock;
    }

    public static void main(String[] args) throws InterruptedException {
        final Set<String> instanceSet = Collections.synchronizedSet(new HashSet<>());
        final TestSingleton lock = new TestSingleton();
        lock.setLock(true);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (!lock.getLock()) {
                            Singleton singleton = Singleton.getInstance();
                            instanceSet.add(singleton.toString());
                        }
                    }
                }
            });
        }

        //给总够的时间让100个线程全
        Thread.sleep(5000);
        lock.setLock(false);
        //保证所有的线程都已经调用了getInstance方法
        Thread.sleep(5000);
        System.out.println("-----并发情况下我们取不到的实例----------");
        for (String instance : instanceSet) {
            System.out.println(instance);
        }
        executorService.shutdownNow();

    }
}
