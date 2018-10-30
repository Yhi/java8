package com.java8.redis;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @author chenyihui
 * @Title: PessClientThread
 * @Description: 顾客线程
 * @date 2018/10/30 19:29
 */
public class PessimisticClientThread implements Runnable {
    // 商品主键
    String key = "prdNum";

    //抢购到商品的顾客列表主键
    String clientList = "clientList";

    String clientName;

    RedisBasedDistributedLock redisBasedDistributedLock;

    Jedis jedis = null;

    public PessimisticClientThread(int num) {
        clientName = "编号=" + num;
        init();
    }

    public void init() {
        jedis = RedisUtil.getInstance().getJedis();
        redisBasedDistributedLock = new RedisBasedDistributedLock(jedis, "lock.lock", 5 * 1000);
    }

    public void run() {
        try {
            // 随机睡眠一下
            Thread.sleep((int) (Math.random() * 5000));
        } catch (InterruptedException e1) {
        }

        while (true) {
            //先判断缓存是否有商品
            if (Integer.valueOf(jedis.get(key)) <= 0) {
                break;
            }

            //缓存还有商品，取锁，商品数目减去1
            System.out.println("顾客:" + clientName + "开始抢商品");
            //等待3秒获取锁，否则返回false
            if (redisBasedDistributedLock.tryLock(3, TimeUnit.SECONDS)) {
                //再次取得商品缓存数目
                int prdNum = Integer.valueOf(jedis.get(key));
                if (prdNum > 0) {
                    //商品数减1
                    jedis.decr(key);
                    // 抢到商品记录一下
                    jedis.sadd(clientList, clientName);
                    System.out.println("好高兴，顾客:" + clientName + "抢到商品");
                } else {
                    System.out.println("悲剧了，库存为0，顾客:" + clientName + "没有抢到商品");
                }
                redisBasedDistributedLock.unlock();
                break;
            }
        }
        //释放资源
        redisBasedDistributedLock = null;
        RedisUtil.returnResource(jedis);
    }

}