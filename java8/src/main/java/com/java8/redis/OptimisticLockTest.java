package com.java8.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @author chenyihui
 * @Title: OptimisticLockTest
 * @Description: redis乐观锁实例
 * @date 2018/10/30 19:52
 */
public class OptimisticLockTest {


    /**
    　　* @Description: TODO
    　　* @param ${tags}
    　　* @return ${return_type}
    　　* @throws
    　　* @author chenyihui
    　　* @date 2018/10/30 19:58
    　　*/
    public static void main(String[] args) throws InterruptedException {
        long starTime=System.currentTimeMillis();

        //初始化商品个数
        initPrduct();
        //抢商品
        initClient();
        //输出结果
        printResult();

        long endTime=System.currentTimeMillis();
        long Time=endTime-starTime;
        System.out.println("程序运行时间： "+Time+"ms");

    }

    /**
     * 输出结果
     */
    public static void printResult() {
        Jedis jedis = RedisUtil.getInstance().getJedis();
        Set<String> set = jedis.smembers("clientList");

        int i = 1;
        for (String value : set) {
            System.out.println("第" + i++ + "个抢到商品，"+value + " ");
        }

        RedisUtil.returnResource(jedis);
    }

    /**
     * 初始化顾客开始抢商品
     */
    public static void initClient() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 150, 5,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
      // ExecutorService executor = Executors.newCachedThreadPool();
        //模拟客户数目
        int clientNum = 10000;
        for (int i = 0; i < clientNum; i++) {
            executor.execute(new  OptimisticClientThread(i));
        }
        executor.shutdown();
        while(true){
            if(executor.isTerminated()){
                System.out.println("所有的线程都结束了！");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化商品个数
     */
    public static void initPrduct() {
        //商品个数
        int prdNum = 100;
        String key = "prdNum";
        // 抢购到商品的顾客列表
        String clientList = "clientList";

        Jedis jedis = RedisUtil.getInstance().getJedis();

        if (jedis.exists(key)) {
            jedis.del(key);
        }

        if (jedis.exists(clientList)) {
            jedis.del(clientList);
        }

        jedis.set(key, String.valueOf(prdNum));// 初始化
        RedisUtil.returnResource(jedis);
    }

}
