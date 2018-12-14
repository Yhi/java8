package com.java8.redis;

import com.java8.redis.seckill.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

/**
 * @author chenyihui
 * @Title: DelKey
 * @Description:
 * 在redis里,允许模糊查询key
 * 有3个通配符 *, ? ,[]
 *: 通配任意多个字符
 * ?: 通配单个字符

[]: 通配括号内的某1个字符
 * @date 2018/11/7 20:10
 */
public class DelKey {

    public static void batchDel(String pre_str){
        Jedis jedis = RedisUtil.getInstance().getJedis();
        Set<String> set = jedis.keys(pre_str +"*");
        Iterator<String> it = set.iterator();
        while(it.hasNext()){

            String keyStr = it.next();
            System.out.println(keyStr);
            jedis.del(keyStr);
        }

        jedis.close();
    }

}
