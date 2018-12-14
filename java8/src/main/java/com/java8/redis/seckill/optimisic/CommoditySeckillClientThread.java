package com.java8.redis.seckill.optimisic;

import org.springframework.data.redis.connection.jedis.JedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author chenyihui
 * @Title: OptimisticClientThread
 * @Description: 商品秒杀 采用redis 乐观锁
 * @date 2018/10/30 19:46
 */
public class CommoditySeckillClientThread implements Runnable {


    Jedis jedis = null;


    String clientName;

    public CommoditySeckillClientThread(int num) {
        clientName = "商品编号=" + num;
    }

    @Override
    public void run() {

        try {
            // 随机睡眠一下
            Thread.sleep((int) (Math.random() * 3000));
        } catch (InterruptedException e1) {

        }
        while (true) {
            System.out.println("顾客:" + clientName + "开始抢商品");
            jedis = RedisUtil.getInstance().getJedis();
            try {
                jedis.watch(RedisUtil.key);
                // 获取当前商品个数
                int prdNum = Integer.parseInt(jedis.get(RedisUtil.key));
                if (prdNum > 0) {
                    //事务的开启
                    Transaction transaction = jedis.multi();
                    //商品的数的扣减
                    transaction.set(RedisUtil.key, String.valueOf(prdNum - 1));
                    //扣减的命令执行
                    List<Object> result = transaction.exec();

                    if (result == null || result.isEmpty()) {
                        System.out.println("对不起，顾客:" + clientName + "没有抢到商品");
                    } else {
                        // 抢到商品记录一下
                        jedis.sadd(RedisUtil.clientList, clientName);
                        jedis.expire(RedisUtil.clientList,300);
                        System.out.println("恭喜!，顾客:" + clientName + "抢到商品");
                        break;
                    }
                } else {
                    System.out.println("别抢了，库存为0，顾客:" + clientName + "没有抢到商品");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //保证下一个命令不会受到影响
                jedis.unwatch();
                RedisUtil.returnResource(jedis);
            }

        }
    }

}