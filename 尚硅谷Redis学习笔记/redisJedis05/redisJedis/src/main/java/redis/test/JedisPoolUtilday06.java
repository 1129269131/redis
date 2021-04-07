package redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Create by koala on 2021-04-06
 * JedisPool：双重检查懒汉式单例
 */
public class JedisPoolUtilday06 {
    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtilday06(){
    }

    public static synchronized JedisPool getJedisPoolInstance(){
        if(null == jedisPool){
            synchronized (JedisPoolUtilday06.class){
                if(null == jedisPool){
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(1000);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWaitMillis(100 * 1000);
                    poolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(poolConfig,"192.168.56.10", 6382);
                }
            }
        }

        return jedisPool;
    }

    public static void release(JedisPool jedisPool, Jedis jedis){
        if(null != jedis){
            jedisPool.returnResourceObject(jedis);
        }
    }

}
