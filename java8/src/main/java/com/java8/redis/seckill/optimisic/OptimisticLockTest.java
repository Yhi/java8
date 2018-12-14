package com.java8.redis.seckill.optimisic;

import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @author chenyihui
 * @Title: OptimisticLockTest
 * @Description: 秒杀测试类
 * @date 2018/10/30 19:52
 */
public class OptimisticLockTest {


    /**
    　　* @Description: test
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

        System.out.println("秒杀抢商品程序运行时间： "+Time+"ms");

    }



    /**
    　　* @Description: 商品 顾客数据初始化
    　　* @throws
    　　* @author chenyihui
    　　* @date 2018/10/31 11:55
    　　*/
    public static void initPrduct() {
        //商品个数
       final int prdNum = 100;

        Jedis jedis = RedisUtil.getInstance().getJedis();

        if (jedis.exists(RedisUtil.key)) {
            jedis.del(RedisUtil.key);
        }

        if (jedis.exists(RedisUtil.clientList)) {
            jedis.del(RedisUtil.clientList);
        }
        // 初始化　
        jedis.set(RedisUtil.key, String.valueOf(prdNum));

        RedisUtil.returnResource(jedis);
    }


    /**
     * 初始化顾客开始抢商品
     */
    /**
    　　* @Description: 开始抢商品
    　　* @throws
    　　* @author chenyihui
    　　* @date 2018/10/30 15:54
    　　*/
    public static void initClient() {
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 150, 5,
//                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());

        //為了方便測試 沒有收到创建线程池 生产环境不建议这样创建
       ExecutorService executor = Executors.newCachedThreadPool();
        //模拟客户数目
        int clientNum = 10000;
        for (int i = 1; i < clientNum; i++) {
            executor.execute(new CommoditySeckillClientThread(i));
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
     　　* @Description: 输出结果
     　　* @author chenyihui
     　　* @date 2018/10/31 11:15
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

}
