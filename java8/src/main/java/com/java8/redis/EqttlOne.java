package com.java8.redis;

import com.java8.redis.seckill.RedisUtil;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author chenyihui
 * @Title: EqttlOne
 * @Description: TODO
 * @date 2018/11/7 19:16
 */
public class EqttlOne {

    public static void  getTtl() throws IOException {
        Jedis jedis = RedisUtil.getInstance().getJedis();

        File file = new File("E:\\redis_timeOut.txt");
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
        BufferedWriter bufferedWriter = new BufferedWriter(write);
        Set<String> keySet = jedis.keys("*");

        Map<Object, Object> map = new HashMap<Object, Object>();

        for (String key : keySet) {
            // Object obj = jedis.get(key);
            long ttl = jedis.ttl(key);
            if (ttl == -1) {
                bufferedWriter.newLine();
                bufferedWriter.write("key:" + key );
                System.out.println(key);
            }
        }
        bufferedWriter.close();
        jedis.close();


    }
}
