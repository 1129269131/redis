package redis.test;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Create by koala on 2021-04-06
 * Jedis常用API：
 *  5 + 1：一个key，五大数据类型
 */
public class TestAPIday02 {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.56.10", 6382);

        //key
        System.out.println("------------ key -------------");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println("jedis.exists===>" + jedis.exists("k1"));
        System.out.println(jedis.ttl("k1"));

        //String
        System.out.println("------------ String -------------");
        jedis.set("k1", "v1");
        jedis.append("k1", "myredis");
        System.out.println(jedis.get("k1"));
        jedis.mset("s1", "v1", "s2", "v2", "s3", "v3");
        System.out.println(jedis.mget("s1", "s2", "s3"));

        //list
        System.out.println("------------ list -------------");
        jedis.lpush("list", "l1", "l2", "l3");
        List<String> list = jedis.lrange("list", 0, -1);
        System.out.println(list);

        //set
        System.out.println("------------ set -------------");
        jedis.sadd("set", "s1", "s2", "s3");
        Set<String> set = jedis.smembers("set");
        System.out.println(set);
        jedis.srem("set", "s1");
        System.out.println(jedis.smembers("set").size());

        //hash
        System.out.println("------------ hash -------------");
        jedis.hset("hash", "userName", "lisi");
        System.out.println(jedis.hget("hash", "userName"));
        Map<String, String> map = new HashMap<>();
        map.put("telPhone", "13811814763");
        map.put("address", "atguigu");
        map.put("email", "abc@163.com");
        jedis.hmset("hash2", map);
        List<String> hmget = jedis.hmget("hash2", "telPhone", "address", "email");
        System.out.println(hmget);

        //zset
        System.out.println("------------ zset -------------");
        jedis.zadd("zset", 60d, "z1");
        jedis.zadd("zset", 70d, "z2");
        jedis.zadd("zset", 80d, "z3");
        Set<String> zset = jedis.zrange("zset", 0, -1);
        System.out.println(zset);
    }

}
