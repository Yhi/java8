package com.java8.redis;

import com.alibaba.fastjson.JSON;
import com.java8.redis.seckill.RedisUtil;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * @author chenyihui
 * @Title: App
 * @Description: TODO
 * @date 2018/10/31 16:41
 */
public class App {

    public static void main(String[] args) throws IOException {

        // EqttlOne.getTtl();
        long start = System.currentTimeMillis();
        Jedis jedis = RedisUtil.getInstance().getJedis();
        System.out.println("------------------");
/*
        String level1=jedis.get("sz.level1.enableregion.key");
        String level2=jedis.get("sz.level2.enableregion.key");
        String level3=jedis.get("sz.level3.enableregion.key");
        System.out.println(level1);
        System.out.println(level2);

        System.out.println(level3);
           long end = System.currentTimeMillis();
        System.out.println(end - start);
*/


       // String memberJson= hget("SZ_LOGIN_NEW_KEY","10004045439", String.class);
       // System.out.println(memberJson);




        String pattern="*_openId3";
        DelKey.batchDel(pattern);

      //  Set<String> set = jedis.keys("*_openId2");

/*
        jedis.set("10000868559_openId2","2");

        jedis.set("10000848295_openId2","3");

        jedis.set("10000691365_openId2","4");

        jedis.set("10000673205_openId2","5");*/
        jedis.close();

    }


    protected static  <T> T hget(final String key, final String field, final Class<T> clazz) {
        Jedis jedis = RedisUtil.getInstance().getJedis();
            final String jsonVal = jedis.hget(key, field);
            if (clazz.isAssignableFrom(String.class)) {
                return (T) jsonVal;
            }
            jedis.close();
            return JSON.parseObject(jsonVal, clazz);
    }


}
