package com.java8.redis.seckill;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyihui
 * @Title: SZACTStockService
 * @Description: TODO
 * @date 2018/10/31 16:44
 */
public class SZACTStockService {
    /** 扣减库存Lua脚本 */
    private static final String LUA_REDUCE_STOCK_SCRIPT = "if (redis.call('exists', KEYS[1]) == 1) then local stock = tonumber(redis.call('get', KEYS[1])); if (stock == -1) then return 1; end; if (stock > 0) then redis.call('incrby', KEYS[1], -1); return stock; end; return 0; end; return -1;";

    /**
     * 查询当前库存
     * @param stockKey 缓存的库存KEY
     * @return 库存值
     */
    public long getStock(final String stockKey) {
        final Jedis jedis = null;// TODO 缓存池获取
        final String stockStr = jedis.get(stockKey);
        return Long.parseLong(stockStr);
    }

    /**
     * 减库存
     * @param stockKey 缓存的库存KEY
     * @return 0：库存不足，-1：库存未初始化，大于0：剩余库存（扣减之前剩余的库存）
     */
    public long reduceStock(final String stockKey) {
        final Jedis jedis = null;// TODO 缓存池获取
        long stock = reduceStock(jedis, stockKey);
        if (stock == -1) {// 未初始化；如果提前预设好，该步骤可省略
            // TODO 初始化库存值，jedis.set(key, val, "NX", "EX", seconds);
        }
        return stock;
    }

    /**
     * 执行减库存
     * <p/><b>redis中缓存的库存值：-1表示库存无上限，直接返回1，0表示没有库存，大于0表示有剩余库存</b>
     * @param REDIS连接
     * @param stockKey 缓存的库存KEY
     * @return 0：库存不足，-1：库存未初始化，大于0：剩余库存（扣减之前剩余的库存）
     */
    private long reduceStock(final Jedis jedis, final String stockKey) {
        final List<String> keys = new ArrayList<String>();
        keys.add(stockKey);
        final List<String> args = new ArrayList<String>();
        return (Long) jedis.eval(LUA_REDUCE_STOCK_SCRIPT, keys, args);
    }
}
