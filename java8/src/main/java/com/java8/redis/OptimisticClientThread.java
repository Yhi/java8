package com.java8.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author chenyihui
 * @Title: OptimisticClientThread
 * @Description: redis 乐观锁
 * @date 2018/10/30 19:46
 */
public class OptimisticClientThread implements Runnable {

    
    Jedis jedis = null;

    // 商品主键
    String key = "prdNum";

    // 抢购到商品的顾客列表主键
    String clientList = "clientList";

    String clientName;

    public OptimisticClientThread(int num) {
        clientName = "编号=" + num;
    }

    public void run() {
        try {
            // 随机睡眠一下
            Thread.sleep((int)(Math.random()*5000));
        } catch (InterruptedException e1) {
        }
        while (true) {
            System.out.println("顾客:" + clientName + "开始抢商品");
            jedis = RedisUtil.getInstance().getJedis();
            try {
                jedis.watch(key);
                // 当前商品个数
                int prdNum = Integer.parseInt(jedis.get(key));
                if (prdNum > 0) {
                    Transaction transaction = jedis.multi();
                    transaction.set(key, String.valueOf(prdNum - 1));
                    List<Object> result = transaction.exec();
                    if (result == null || result.isEmpty()) {
                        // 可能是watch-key被外部修改，或者是数据操作被驳回
                        System.out.println("悲剧了，顾客:" + clientName + "没有抢到商品");
                    } else {
                        // 抢到商品记录一下
                        jedis.sadd(clientList, clientName);
                        System.out.println("好高兴，顾客:" + clientName + "抢到商品");
                        break;
                    }
                } else {
                    System.out.println("悲剧了，库存为0，顾客:" + clientName + "没有抢到商品");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.unwatch();
                RedisUtil.returnResource(jedis);
            }

        }
    }

}