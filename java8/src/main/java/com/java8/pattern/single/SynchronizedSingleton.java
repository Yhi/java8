package com.java8.pattern.single;


/**
 * @author chenyihui
 * @version 1.0
 * @Description  双重加锁
 *
 *再次判断了synchronizedSingleton是否为null的原因
 *
 *    假设我们去掉同步块中的是否为null的判断，有这样一种情况，假设A线程和B线程都在同步块外面判断了synchronizedSingleton为null，
 *结果A线程首先获得了线程锁，进入了同步块，然后A线程会创造一个实例，此时synchronizedSingleton已经被赋予了实例，A线程退出同步块，
 *直接返回了第一个创造的实例，此时B线程获得线程锁，也进入同步块，此时A线程其实已经创造好了实例，B线程正常情况应该直接返回的，
 *但是因为同步块里没有判断是否为null，直接就是一条创建实例的语句，所以B线程也会创造一个实例返回，此时就造成创造了多个实例的情况。
 *
 * 经过分析看起来可能问题不大， 蛋如果在进一步深入考虑的话，其实仍然有问题的。在JVM角度探索，有可能（注意，只是有可能）是有问题的
 * JVM在创建对象新的对象时，主要经过三步、
 * 1.分配内存
 * 2.初始化构造器
 * 3.将对象指向分配的内存的地址
 *
 * 这种顺序在双重加锁是没有问题的。因为这种情况下JVM是完成了整个对象的构造才将内存地址交给对象。但是如果2和3步骤是相反的（2和3
 * 可能是相反的是因为JVM会针对字节码调优，而其中的一项调优便是调整指令的执行顺序），就会出现问题
 *
 * 因为这时将会 先将内存地址赋予对象，针对双重加锁，就是说先将分配好的内存地址指给 synchronizedSingleton，然后在进行初始化构造器
 * 这时候后面的线程请求getInstance方法时，会认为synchronizedSingleton对象已经实例化了，直接返回一个引用。如果在初始化构造器之前，
 * 这个线程使用了synchronizedSingleton，就会产生莫名的错误。
 * @date 2018/7/16
 */
public class SynchronizedSingleton {

    private volatile static SynchronizedSingleton synchronizedSingleton;

    private SynchronizedSingleton() {
    }


    public static SynchronizedSingleton getInstance() {
        if (synchronizedSingleton == null) {
            synchronized (SynchronizedSingleton.class) {
                if (synchronizedSingleton == null) {
                    synchronizedSingleton = new SynchronizedSingleton();
                }
            }
        }

        return synchronizedSingleton;
    }
}
