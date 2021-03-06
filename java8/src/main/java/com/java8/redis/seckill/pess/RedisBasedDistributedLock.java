package com.java8.redis.seckill.pess;

import com.java8.redis.seckill.optimisic.AbstractLock;
import redis.clients.jedis.Jedis;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.TimeUnit;

/**
 * @author Shanzhen
 * @Title: RedisBasedDistributedLock
 * @Description: 基于Redis的SETNX操作实现的分布式锁 获取锁时最好用lock(long time, TimeUnit unit), 以免网路问题而导致线程一直阻塞
 * @date 2018/10/3019:15
 */

public class RedisBasedDistributedLock extends AbstractLock {

    private Jedis jedis;

    // 锁的名字
    protected String lockKey;

    // 锁的有效时长(毫秒)
    protected long lockExpires;

    public RedisBasedDistributedLock(Jedis jedis, String lockKey, long lockExpires) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.lockExpires = lockExpires;
    }

    // 阻塞式获取锁的实现
    protected boolean lock(boolean useTimeout, long time, TimeUnit unit, boolean interrupt) throws InterruptedException {
        System.out.println("test1");
        if (interrupt) {
            checkInterruption();
        }

        System.out.println("test2");
        long start = System.currentTimeMillis();
        long timeout = unit.toMillis(time);

        while (useTimeout ? isTimeout(start, timeout) : true) {
            System.out.println("test3");
            if (interrupt) {
                checkInterruption();
            }
            // 锁超时时间
            long lockExpireTime = System.currentTimeMillis() + lockExpires + 1;
            String stringOfLockExpireTime = String.valueOf(lockExpireTime);

            System.out.println("test4");
            if (jedis.setnx(lockKey, stringOfLockExpireTime) == 1) { // 获取到锁
                System.out.println("test5");
                //成功获取到锁, 设置相关标识
                locked = true;
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }

            System.out.println("test6");
            String value = jedis.get(lockKey);
            // lock is expired
            if (value != null && isTimeExpired(value)) {
                System.out.println("test7");
                // 假设多个线程(非单jvm)同时走到这里
                //原子操作
                String oldValue = jedis.getSet(lockKey, stringOfLockExpireTime);
                // 但是走到这里时每个线程拿到的oldValue肯定不可能一样(因为getset是原子性的)
                // 加入拿到的oldValue依然是expired的，那么就说明拿到锁了
                System.out.println("test8");
                if (oldValue != null && isTimeExpired(oldValue)) {
                    System.out.println("test9");
                    //成功获取到锁, 设置相关标识
                    locked = true;
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else {
                // TODO lock is not expired, enter next loop retrying
            }
        }
        System.out.println("test10");
        return false;
    }

    public boolean tryLock() {
        // 锁超时时间
        long lockExpireTime = System.currentTimeMillis() + lockExpires + 1;
        String stringOfLockExpireTime = String.valueOf(lockExpireTime);

        // 获取到锁
        if (jedis.setnx(lockKey, stringOfLockExpireTime) == 1) {
            // 成功获取到锁, 设置相关标识
            locked = true;
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }

        String value = jedis.get(lockKey);
        // lock is expired
        if (value != null && isTimeExpired(value)) {
            // 假设多个线程(非单jvm)同时走到这里
            //原子操作
            String oldValue = jedis.getSet(lockKey, stringOfLockExpireTime);
            // 但是走到这里时每个线程拿到的oldValue肯定不可能一样(因为getset是原子性的)
            // 假如拿到的oldValue依然是expired的，那么就说明拿到锁了
            if (oldValue != null && isTimeExpired(oldValue)) {
                //成功获取到锁, 设置相关标识
                locked = true;
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
        } else {
            // TODO lock is not expired, enter next loop retrying
        }

        return false;
    }

    /**
     * Queries if this lock is held by any thread.
     *
     * @return {@code true} if any thread holds this lock and {@code false}
     * otherwise
     */

    public boolean isLocked() {
        if (locked) {
            return true;
        } else {
            String value = jedis.get(lockKey);
            // TODO 这里其实是有问题的, 想:当get方法返回value后, 假设这个value已经是过期的了,
            // 而就在这瞬间, 另一个节点set了value, 这时锁是被别的线程(节点持有), 而接下来的判断
            // 是检测不出这种情况的.不过这个问题应该不会导致其它的问题出现, 因为这个方法的目的本来就
            // 不是同步控制, 它只是一种锁状态的报告.
            return !isTimeExpired(value);
        }
    }

    @Override
    protected void unlock0() {
        // 判断锁是否过期
        String value = jedis.get(lockKey);
        if (!isTimeExpired(value)) {
            doUnlock();
        }
    }

    private void checkInterruption() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
    }

    private boolean isTimeExpired(String value) {
        return Long.parseLong(value) < System.currentTimeMillis();
    }

    private boolean isTimeout(long start, long timeout) {
        return start + timeout > System.currentTimeMillis();
    }

    private void doUnlock() {
        jedis.del(lockKey);
    }

    public Condition newCondition() {
        // TODO Auto-generated method stub
        return null;
    }

}
