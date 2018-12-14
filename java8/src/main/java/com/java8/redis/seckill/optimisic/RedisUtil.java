package com.java8.redis.seckill.optimisic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @author chenyihui
 * @Title: AbstractLock
 * @Description: redis 通用工具类
 * @date 2018/10/30 19:34
 */
public class RedisUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    private static JedisPool pool = null;

    private static RedisUtil ru = new RedisUtil();


    // 商品主键
    public final static String key = "prdNum";

    // 抢购到商品的顾客列表key
    public final static String clientList = "clientList";


    public static void main(String[] args) {
        RedisUtil redisUtil = RedisUtil.getInstance();
        redisUtil.set("test", "test");
        LOGGER.info(redisUtil.get("test"));
    }

    private RedisUtil() {
        if (pool == null) {
            String ip = "127.0.0.1";
            int port = 6379;
            JedisPoolConfig config = new JedisPoolConfig();
            //最大连接数, 默认8个 这个暂时跟顾客保持一致
            config.setMaxTotal(10000);
            // 最大空闲连接数, 默认8个
            config.setMaxIdle(2000);
            // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时则直接抛出JedisConnectionException, 小于零:阻塞不确定的时间,  默认-1
            config.setMaxWaitMillis(1000 * 100);
            //在获取连接的时候检查有效性, 默认false
            config.setTestOnBorrow(true);
            pool = new JedisPool(config, ip, port, 100000);
        }

    }

    public Jedis getJedis() {
        Jedis jedis = pool.getResource();
        return jedis;
    }

    public static RedisUtil getInstance() {
        return ru;
    }

    /**
     * 　　* @Description: 通过key获取储存在redis中的value 并释放连接
     * 　　* @param key
     * 　　* @return 成功返回value 失败返回null
     * 　　* @throws
     * 　　* @author chenyihui
     * 　　* @date 2018/10/30 19:21
     */
    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            returnResource(pool, jedis);
        }
        return value;
    }


    /**
     * 　　* @Description: 向redis存入key和value,并释放连接资源 如果key已经存在 则覆盖
     * 　　* @param key
     * 　　* @return 成功返回ok 失败返回0
     * 　　* @throws
     * 　　* @author chenyihui
     * 　　* @date 2018/10/30 19:51
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
            return "0";
        } finally {
            returnResource(pool, jedis);
        }
    }

    /**
     * 　　* @Description: 删除指定的key,也可以传入一个包含key的数组
     * 　　* @param key 也可以是 string 数组
     * 　　* @return 返回删除成功的个数
     * 　　* @throws
     * 　　* @author chenyihui
     * 　　* @date 2018/10/31 11:24
     */
    public Long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.del(keys);
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
            return 0L;
        } finally {
            returnResource(pool, jedis);
        }
    }


    /**
     * @param key
     * @return 成功返回1 如果存在 和 发生异常 返回 0
     * @throws
     * @Description: 设置key value,如果key已经存在则返回0,nx==> not exist
     * @author chenyihui
     * @date 2018/10/31 11:45
     */
    public Long setnx(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.setnx(key, value);
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
            return 0L;
        } finally {
            returnResource(pool, jedis);
        }
    }

    /**
     * 　　* @Description: 设置key value并制定这个键值的有效期
     * 　　* @param key
     * 　 * @param value
     *
     * @param seconds 单位:秒
     *                　　* @return 成功返回OK 失败和异常返回null
     *                　　* @throws
     *                　　* @author chenyihui
     *                　　* @date 2018/10/31 11:58
     *
     */
    public String setex(String key, String value, int seconds) {
        Jedis jedis = null;
        String res = null;
        try {
            jedis = pool.getResource();
            res = jedis.setex(key, seconds, value);
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
        } finally {
            returnResource(pool, jedis);
        }
        return res;
    }


    /**
     * 返还到连接池
     *
     * @param pool
     * @param jedis
     */
    public static void returnResource(JedisPool pool, Jedis jedis) {
        try {
            jedis = pool.getResource();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    /**
     * 返还到连接池
     *
     * @param jedis
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
