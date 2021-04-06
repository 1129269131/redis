package redis.test;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Create by koala on 2021-04-06
 */
public class TestAPIday02 {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.56.10", 6382);

        jedis.set("k1","v1");
        jedis.set("k2","v2");
        jedis.set("k3","v3");

        System.out.println(jedis.get("k3"));

        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

    }

}
