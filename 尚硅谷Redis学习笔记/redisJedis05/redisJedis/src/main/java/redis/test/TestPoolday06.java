package redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Create by koala on 2021-04-06
 * JedisPool测试
 */
public class TestPoolday06 {

    public static void main(String[] args) {
        JedisPool jedisPool = JedisPoolUtilday06.getJedisPoolInstance();
        JedisPool jedisPool2 = JedisPoolUtilday06.getJedisPoolInstance();
        System.out.println(jedisPool == jedisPool2);

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("aa","bb");
            System.out.println(jedis.get("aa"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JedisPoolUtilday06.release(jedisPool, jedis);
        }
    }

}
