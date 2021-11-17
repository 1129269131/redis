package com.koala.redis_redlock.test;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * day09：
 *  看门狗
 * Create by koala on 2021-11-16
 */
public class WatchDogDemo
{
    public static final String LOCKKEY = "AAA";

    private static Config config;
    private static Redisson redisson;

    static {
        config = new Config();
        config.useSingleServer().setAddress("redis://"+"127.0.0.1"+":6379").setDatabase(0);
        redisson = (Redisson)Redisson.create(config);
    }

    public static void main(String[] args)
    {
        RLock redissonLock = redisson.getLock(LOCKKEY);

        redissonLock.lock();
        try
        {
            System.out.println("1111-------biz");
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(25); } catch (InterruptedException e) { e.printStackTrace(); }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(redissonLock.isLocked() && redissonLock.isHeldByCurrentThread())
            {
                redissonLock.unlock();
            }
        }

        System.out.println(Thread.currentThread().getName() + " main ------ ends.");

        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        redisson.shutdown();
    }
}
