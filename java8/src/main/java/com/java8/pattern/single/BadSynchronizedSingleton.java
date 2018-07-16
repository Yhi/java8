package com.java8.pattern.single;


/**
 * @author chenyihui
 * @version 1.0
 * @Description 整个获取实例的方法同步 ,这样在一个线程访问这个方法时，其他所有的线程都要处于挂起状态
 *              倒是避免了刚才同步访问创造出多个实例的危险，但是这样的设计 实在是fuck，这样会造成
 *              很多无谓的等待，我表示很愤怒 类名叫bad
 * @date 2018/7/16
 */
public class BadSynchronizedSingleton {


    private static BadSynchronizedSingleton badSynchronizedSingleton;

    private BadSynchronizedSingleton() {
    }

    //给出一个公共的静态方法返回一个单一实例
    public synchronized static BadSynchronizedSingleton getInstance() {
        if (badSynchronizedSingleton == null) {
            badSynchronizedSingleton = new BadSynchronizedSingleton();

        }
        return  badSynchronizedSingleton;
    }
}
